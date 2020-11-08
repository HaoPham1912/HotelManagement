
INSERT INTO `bookinghotel`.`customertypes` (`typename`) VALUES ('NORMAL');
INSERT INTO `bookinghotel`.`customertypes` (`typename`) VALUES ('GOLD');
INSERT INTO `bookinghotel`.`customertypes` (`typename`) VALUES ('DIAMOND');

INSERT INTO `bookinghotel`.`roles` (`roleid`, `rolename`) VALUES ('1', 'CUSTOMER');
INSERT INTO `bookinghotel`.`roles` (`roleid`, `rolename`) VALUES ('2', 'ADMIN');
INSERT INTO `bookinghotel`.`roles` (`roleid`, `rolename`) VALUES ('3', 'EMPLOYEE');


INSERT INTO `bookinghotel`.`accounts` (`password`, `status`, `username`) VALUES ('admin', b'1', 'admin');
INSERT INTO `bookinghotel`.`accounts` (`password`, `status`, `username`) VALUES ('customer', b'1', 'customer');
INSERT INTO `bookinghotel`.`accounts` (`password`, `status`, `username`) VALUES ('employee', b'1', 'employee');

INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('2', '1');
INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('3', '3');
INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('1', '3');

INSERT INTO `bookinghotel`.`customers` (`cuscode`, `idcard`, `name`, `phone`,`email`, `accountid`, `typecustomerid`) VALUES ('NOR1', '123321123', 'Nguyen van a', '0223331234','vana@gmail.com', '2', '1');

INSERT INTO `bookinghotel`.`branchs` (`address`, `branchcode`, `name`, `status`) VALUES ('Ho Chi Minh', 'CN1', 'H2 Quan 9', b'1');
INSERT INTO `bookinghotel`.`branchs` (`address`, `branchcode`, `name`, `status`) VALUES ('Ha Noi', 'CN2', 'H2 Ho Guom', b'1');
INSERT INTO `bookinghotel`.`branchs` (`address`, `branchcode`, `name`, `status`) VALUES ('Da Nang', 'CN3', 'H2 Hoi An', b'1');

INSERT INTO `bookinghotel`.`employees` (`empcode`, `idcard`, `name`, `phone`, `accountid`, `branchid`) VALUES ('EMP1', '456456897', 'Nguyen Thi Nhan Vien', '0223321456', '3', '2');

INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`, `branchid`) VALUES ('Peace1', '1000000', 'RHCM1', '2');
INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`, `branchid`) VALUES ('Peace2', '500000', 'RHCM2', '2');
INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`, `branchid`) VALUES ('Peach3', '500000', 'RHN1', '3');
INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`, `branchid`) VALUES ('Peach4', '800000', 'RDN1', '1');

INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`, `roomid`) VALUES ('2', 'Couple', '200000', '1');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`, `roomid`) VALUES ('1', 'Single', '150000', '1');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`, `roomid`) VALUES ('2', 'Couple', '200000', '2');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`, `roomid`) VALUES ('2', 'Couple', '200000', '3');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`, `roomid`) VALUES ('1', 'Single', '150000', '2');

INSERT INTO `bookinghotel`.`services` (`name`, `price`, `servicecode`) VALUES ('Ho Boi', '200000', 'HB1');
INSERT INTO `bookinghotel`.`services` (`name`, `price`, `servicecode`) VALUES ('Xong Hoi', '150000', 'XH1');
INSERT INTO `bookinghotel`.`services` (`name`, `price`, `servicecode`) VALUES ('HoneyMoon', '1000000', 'HM1');
INSERT INTO `bookinghotel`.`services` (`name`, `price`, `servicecode`) VALUES ('Giat Ui', '50000', 'GU1');

INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('1', '1');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('1', '2');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('1', '3');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('2', '1');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('2', '3');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('3', '2');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('4', '1');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('4', '2');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('4', '3');
INSERT INTO `bookinghotel`.`service_room` (`service_id`, `room_id`) VALUES ('4', '4');

INSERT INTO `bookinghotel`.`promotions` (`custype`, `enddate`, `percent`, `code`, `startdate`) VALUES ('GOLD', '2020/12/19', '10', 'KM1', '2020/12/10');
INSERT INTO `bookinghotel`.`promotions` (`custype`, `enddate`, `percent`, `code`, `startdate`) VALUES ('DIAMOND', '2020/12/31', '15', 'NY1', '2020/12/25');

INSERT INTO `bookinghotel`.`bills` (`createdate`, `totalprice`, `customerid`) VALUES ('2020/11/01', '2000000', '1');
INSERT INTO `bookinghotel`.`bills` (`createdate`, `totalprice`, `customerid`) VALUES ('2020/11/05', '2500000', '1');

INSERT INTO `bookinghotel`.`bookings` (`billid`, `bookdate`, `checkindate`, `roomid`, `checkoutdate`, `paidprice`, `status`) VALUES ('1', '2020/10/25', '2020/10/29', '1', '2020/11/1', '1000000', b'1');
INSERT INTO `bookinghotel`.`bookings` (`billid`, `bookdate`, `checkindate`, `roomid`, `checkoutdate`, `paidprice`, `status`) VALUES ('2', '2020/10/19', '2020/11/03', '2', '2020/11/05', '10000000', b'1');
