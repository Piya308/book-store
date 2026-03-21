package com.priyanka.order_service.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<OrderEntity, Long> {
//    List<OrderEntity> findByStatus(OrderStatus status);
//
//    Optional<OrderEntity> findByOrderNumber(String orderNumber);
//
//    default void updateOrderStatus(String orderNumber, OrderStatus status) {
//        OrderEntity order = this.findByOrderNumber(orderNumber).orElseThrow();
//        order.setStatus(status);
//        this.save(order);
//    }
//
//    @Query(
//            """
//        select new com.sivalabs.bookstore.orders.domain.models.OrderSummary(o.orderNumber, o.status)
//        from OrderEntity o
//        where o.userName = :userName
//        """)
//    List<OrderSummary> findByUserName(String userName);
//
//    @Query(
//            """
//        select distinct o
//        from OrderEntity o left join fetch o.items
//        where o.userName = :userName and o.orderNumber = :orderNumber
//        """)
//    Optional<OrderEntity> findByUserNameAndOrderNumber(String userName, String orderNumber);
}
