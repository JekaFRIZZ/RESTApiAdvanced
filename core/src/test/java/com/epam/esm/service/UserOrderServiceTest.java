package com.epam.esm.service;

class UserOrderServiceTest {
    /*private final UserOrderDAO mockUserOrderDao = mock(UserOrderDAO.class);
    private final UserOrderValidator mockUserOrderValidator = mock(UserOrderValidator.class);
    private final UserOrderService userOrderService = new UserOrderService(
            mockUserOrderDao,
            mockUserOrderValidator
    );

    private final List<UserOrder> userOrders = new ArrayList<>();
    private final Integer userOrderId = 1;
    private final UserOrder userOrder = new UserOrder(userOrderId,1, 1, BigDecimal.TEN, LocalDateTime.MIN);
    private final UserOrderDTO userOrderDTO = new UserOrderDTO(userOrderId,1, 1,BigDecimal.TEN);
    private final Optional<UserOrder> optionalUserOrder = Optional.of(userOrder);

    @Test
    public void testShouldGetAllUserOrdersWhenCorrectPaginateParamApplied() {
        int limit = 5;
        int offset = 2;
        when(mockUserOrderDao.getAll(limit, offset)).thenReturn(userOrders);

        List<UserOrder> actual = userOrderService.getAll(limit, offset);

        assertEquals(userOrders, actual);
    }

    @Test
    public void testShouldThrowExceptionWhenIncorrectPaginateParamApplied() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int limit = -5;
            int offset = -5;

            userOrderService.getAll(limit, offset);
        });
    }

    @Test
    public void testShouldGetAllUserOrdersByUserIdWhenCorrectPaginateParamApplied() {
        Integer userId = 1;
        int limit = 5;
        int offset = 5;
        when(mockUserOrderDao.getAllByUserId(userId, limit, offset)).thenReturn(userOrders);

        List<UserOrder> actual = userOrderService.getAllByUserId(userId, limit, offset);

        assertEquals(userOrders, actual);
    }

    @Test
    public void testShouldGetUserOrderById() {
        when(mockUserOrderDao.getById(userOrderId)).thenReturn(optionalUserOrder);

        UserOrder actual = userOrderService.getById(userOrderId);

        assertEquals(userOrder, actual);
    }

    @Test
    public void testShouldThrowExceptionWhenNonExistentUserOrderApplied() {
        Assertions.assertThrows(ResourceExistenceException.class, () -> {
            when(mockUserOrderDao.getById(userOrderId)).thenReturn(Optional.empty());

            userOrderService.getById(userOrderId);
        });
    }

    @Test
    public void testShouldDoNothingWhenDeleteExistingUserOrder() {
        when(mockUserOrderDao.getById(userOrderId)).thenReturn(optionalUserOrder);
        doNothing().when(mockUserOrderDao).deleteById(userOrder);

        userOrderService.deleteById(userOrderId);
    }

    @Test
    public void testShouldThrowExceptionWhenDeleteNonExistentUserOrderApplied() {
        Assertions.assertThrows(ResourceExistenceException.class, () -> {
            when(mockUserOrderDao.getById(userOrderId)).thenReturn(Optional.empty());

            userOrderService.deleteById(userOrderId);
        });
    }
*/
}