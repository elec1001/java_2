package com.practice.spring.orderservice.order.domain;

import com.practice.spring.orderservice.book.Book;
import com.practice.spring.orderservice.book.BookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.practice.spring.orderservice.order.domain.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private  final OrderRepository orderRepository;
    private  final BookClient bookClient;

    public Flux<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Mono<Order> submitOrder(String isbn,int quantity){
        return bookClient.getBookByIsbn(isbn)
                .map(book->buildAcceptedOrder(book,quantity))
                .defaultIfEmpty(buildRejectedOrder(isbn,quantity))
                .flatMap(orderRepository::save);//평탄화
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
