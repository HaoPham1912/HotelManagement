
INSERT INTO `bookinghotel`.`customertypes` (`typename`) VALUES ('NORMAL');
INSERT INTO `bookinghotel`.`customertypes` (`typename`) VALUES ('GOLD');
INSERT INTO `bookinghotel`.`customertypes` (`typename`) VALUES ('DIAMOND');

INSERT INTO `bookinghotel`.`roles` (`roleid`, `rolename`) VALUES ('1', 'CUSTOMER');
INSERT INTO `bookinghotel`.`roles` (`roleid`, `rolename`) VALUES ('2', 'ADMIN');
INSERT INTO `bookinghotel`.`roles` (`roleid`, `rolename`) VALUES ('3', 'EMPLOYEE');


INSERT INTO `bookinghotel`.`accounts` (`password`, `status`, `username`) VALUES ('$2a$10$od0SURdtxoXztnc93Ybo/uKg/.rljLd48P4zqLVuINQC95dx5Xv0W', b'1', 'admin');
INSERT INTO `bookinghotel`.`accounts` (`password`, `status`, `username`) VALUES ('$2a$10$od0SURdtxoXztnc93Ybo/uKg/.rljLd48P4zqLVuINQC95dx5Xv0W', b'1', 'customer');
INSERT INTO `bookinghotel`.`accounts` (`password`, `status`, `username`) VALUES ('$2a$10$od0SURdtxoXztnc93Ybo/uKg/.rljLd48P4zqLVuINQC95dx5Xv0W', b'1', 'employee');

INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('2', '1');
INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('3', '3');
INSERT INTO `bookinghotel`.`account_role` (`account_id`, `role_id`) VALUES ('1', '3');

INSERT INTO `bookinghotel`.`customers` (`cuscode`, `idcard`, `name`, `phone`,`email`, `accountid`, `typecustomerid`) VALUES ('NOR1', '123321123', 'Nguyen van a', '0223331234','vana@gmail.com', '2', '1');

INSERT INTO `bookinghotel`.`branchs` (`address`, `branchcode`,`location`, `name`, `status`) VALUES ('Vinhomes Central Park, Binh Thanh District, Ho Chi Minh City, Viet Nam', 'HCM1','Ho Chi Minh', 'H2 Quan 9', b'1');
INSERT INTO `bookinghotel`.`branchs` (`address`, `branchcode`,`location`, `name`, `status`) VALUES ('44-46 Le Thanh Ton Street, Loc Tho Ward, Nha Trang City, Khanh Hoa Province, Vietnam', 'NT1', 'Nha Trang','H2 Ho Guom', b'1');
INSERT INTO `bookinghotel`.`branchs` (`address`, `branchcode`,`location`, `name`, `status`) VALUES ('341 Tran Hung Dao Street, An Hai Bac Ward, Son Tra District, Da Nang, Viet Nam', 'DN1','Da Nang', 'H2 Hoi An', b'1');

INSERT INTO `bookinghotel`.`employees` (`empcode`, `idcard`, `name`, `email` , `phone`, `accountid`, `branchid`) VALUES ('EMP1', '456456897', 'Nguyen Thi Nhan Vien','emp@gmail.com', '0223321456', '3', '2');

INSERT INTO `bookinghotel`.`cancelpolicy` (`code`, `title`, `detail`,`daylong`) VALUES ('PL1', 'Cancellation should be made at least 07 days prior to arrival date','Cancellation should be made at least 07 days prior to arrival date to avoid the penalty charge. No-show or late cancellation, the hotel reserves the right to charge full nights of the booking.','7');
INSERT INTO `bookinghotel`.`cancelpolicy` (`code`, `title`,`daylong`) VALUES ('PL2', 'Non-refundable','0');

INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`,`policyid`, `branchid`) VALUES ('Peace1', '1000000', 'RHCM1', '1', '2');
INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`,`policyid`, `branchid`) VALUES ('Peace2', '500000', 'RHCM2','2', '2');
INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`,`policyid`, `branchid`) VALUES ('Peach3', '500000', 'RHN1','1', '3');
INSERT INTO `bookinghotel`.`rooms` (`name`, `price`, `roomcode`,`policyid`, `branchid`) VALUES ('Peach4', '800000', 'RDN1','2', '1');

INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`) VALUES ('2', 'Couple', '200000');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`) VALUES ('1', 'Single', '150000');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`) VALUES ('2', 'Couple', '200000');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`) VALUES ('2', 'Couple', '200000');
INSERT INTO `bookinghotel`.`beds` (`ammountpeople`, `name`, `price`) VALUES ('1', 'Single', '150000');

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

UPDATE `bookinghotel`.`branchs` SET `description` = 'Located on the picturesque Saigon river bank, Vinpearl Luxury Landmark 81 is sumptously encapsulated in the beautiful green garden of Vinhomes Central Park.' WHERE (`branchid` = '1');
UPDATE `bookinghotel`.`branchs` SET `description` = 'Vinpearl Condotel Riverfront Da Nang is a modern apartment hotel with a unique view facing the Han River and the Dragon Bridge, with a wide view of the whole city. The hotel follows the neoclassical architecture with the most luxurious design according to international standards. With 5-star quality service and entertainment promising to bring the highest sophisticated experience to visitors.' WHERE (`branchid` = '3');
UPDATE `bookinghotel`.`branchs` SET `description` = 'As a luxury and classy hotel apartment, Vinpearl Condotel Empire Nha Trang is built up with unprecedented options, where customers can not only own a homy comfortable living space, but also enjoy ecotourism right in a coastal city. With a proud 41-storey building standing in the heart of the city, Vinpearl Condotel Empire Nha Trang gives visitors a complete sense of the vibrant urban life.' WHERE (`branchid` = '2');

INSERT INTO `bookinghotel`.`bed_room` (`bed_id`, `room_id`) VALUES ('1', '1');
INSERT INTO `bookinghotel`.`bed_room` (`bed_id`, `room_id`) VALUES ('2', '2');
INSERT INTO `bookinghotel`.`bed_room` (`bed_id`, `room_id`) VALUES ('3', '3');
INSERT INTO `bookinghotel`.`bed_room` (`bed_id`, `room_id`) VALUES ('4', '4');
INSERT INTO `bookinghotel`.`bed_room` (`bed_id`, `room_id`) VALUES ('5', '1');

UPDATE `bookinghotel`.`rooms` SET `amountpeople` = '3' WHERE (`roomid` = '1');
UPDATE `bookinghotel`.`rooms` SET `amountpeople` = '1' WHERE (`roomid` = '2');
UPDATE `bookinghotel`.`rooms` SET `amountpeople` = '2' WHERE (`roomid` = '3');
UPDATE `bookinghotel`.`rooms` SET `amountpeople` = '2' WHERE (`roomid` = '4');
