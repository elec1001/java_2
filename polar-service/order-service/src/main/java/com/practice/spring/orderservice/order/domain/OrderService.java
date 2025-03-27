package com.practice.spring.orderservice.order.domain;

import com.practice.spring.orderservice.book.Book;
import com.practice.spring.orderservice.book.BookClient;
import com.practice.spring.orderservice.event.OrderAcceptedMessage;
import com.practice.spring.orderservice.event.OrderDispatchedMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.practice.spring.orderservice.order.domain.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final static Logger log= LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final BookClient bookClient;
    private final StreamBridge streamBridge;

    public Flux<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Mono<Order> submitOrder(String isbn,int quantity){
        return bookClient.getBookByIsbn(isbn)
                .map(book->buildAcceptedOrder(book,quantity))//Book 정보를 바탕으로 주문 생성
                .defaultIfEmpty(buildRejectedOrder(isbn,quantity))//책 정보가 없으면 거절된 주문 생성
                .flatMap(orderRepository::save) //주문을 DB에 저장 (Mono 평탄화)
                .doOnNext(this::publishOrderAcceptedEvent); // 저장 후 이벤트 발생
    }

    public Flux<Order> consumerOrderDispatchedEvent(Flux<OrderDispatchedMessage> orderDispatchedMessageFlux){
        return orderDispatchedMessageFlux
                .flatMap(orderDispatchedMessage -> orderRepository.findById((orderDispatchedMessage.orderId())))
                .map(this::buildDispatchedOrder)
                .flatMap(orderRepository::save);

    }


    private void publishOrderAcceptedEvent(Order order){
        if(!order.status().equals(ACCEPTED)){
            return;
        }
        OrderAcceptedMessage orderAcceptedMessage=new OrderAcceptedMessage(order.id());
        log.info("Result of sending order accepted event : {}",orderAcceptedMessage.orderId());

        boolean result=streamBridge.send("acceptOrder-out-0",orderAcceptedMessage);
        log.info("Result of sending order accepted event : {},id : {}",result,orderAcceptedMessage.orderId());
    }

    private Order buildDispatchedOrder(Order exsistingOrder){
        return Order.builder()
                .id(exsistingOrder.id())
                .bookIsbn(exsistingOrder.bookIsbn())
                .bookName(exsistingOrder.bookName())
                .quantity(exsistingOrder.quantity())
                .bookPrice(exsistingOrder.bookPrice())
                .status(DISPATCHED)
                .createdDate(exsistingOrder.createdDate())
                .lastModifiedDate(exsistingOrder.lastModifiedDate())
                .version(exsistingOrder.version())
                .build();
    }

    private static Order buildAcceptedOrder(Book book,int quantity){
        return Order.builder()
                .bookIsbn(book.isbn())
                .bookName(book.title()+"-"+book.author())
                .bookPrice(book.price())
                .quantity(quantity)
                .status(ACCEPTED)
                .build();
    }

    private static Order buildRejectedOrder(String bookIsbn,int quantity){
        return Order.builder()
                .bookIsbn(bookIsbn)
                .quantity(quantity)
                .status(REJECTED)
                .build();
    }


}
