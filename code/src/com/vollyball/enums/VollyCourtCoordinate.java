/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.enums;

/**
 *
 * @author nishant.vibhute
 */
public enum VollyCourtCoordinate {

    Service11(1, "Service", 1, 1, 90, 331, 294, 263, 410, 224, 611, 155),
    Service12(2, "Service", 1, 2, 90, 331, 294, 233, 410, 177, 461, 155),
    Service13(3, "Service", 1, 3, 90, 331, 294, 282, 410, 257, 461, 246),
    Service14(4, "Service", 1, 4, 90, 331, 294, 332, 410, 332, 461, 332),
    Service15(5, "Service", 1, 5, 90, 331, 294, 332, 410, 332, 611, 332),
    Service16(6, "Service", 1, 6, 90, 331, 294, 296, 410, 277, 611, 242),
    Service51(7, "Service", 5, 1, 90, 155, 294, 155, 410, 155, 611, 155),
    Service52(8, "Service", 5, 2, 90, 155, 294, 155, 410, 155, 461, 155),
    Service53(9, "Service", 5, 3, 90, 155, 294, 204, 410, 232, 461, 245),
    Service54(10, "Service", 5, 4, 90, 155, 294, 251, 410, 306, 461, 330),
    Service55(11, "Service", 5, 5, 90, 155, 294, 222, 410, 261, 611, 330),
    Service56(12, "Service", 5, 6, 90, 155, 294, 188, 410, 206, 611, 243),
    Service61(13, "Service", 6, 1, 90, 242, 294, 208, 410, 190, 611, 155),
    Service62(14, "Service", 6, 2, 90, 242, 294, 193, 410, 164, 461, 155),
    Service63(15, "Service", 6, 3, 90, 242, 294, 242, 410, 242, 461, 242),
    Service64(16, "Service", 6, 4, 90, 242, 294, 290, 410, 317, 461, 331),
    Service65(17, "Service", 6, 5, 90, 242, 294, 276, 410, 296, 611, 331),
    Service66(18, "Service", 6, 6, 90, 242, 294, 242, 410, 242, 611, 242),
    Attack11(19, "Attack", 1, 1, 212, 331, 294, 297, 410, 244, 611, 155),
    Attack12(20, "Attack", 1, 2, 212, 331, 294, 273, 410, 188, 461, 155),
    Attack13(21, "Attack", 1, 3, 212, 331, 294, 304, 410, 264, 461, 242),
    Attack14(22, "Attack", 1, 4, 212, 331, 294, 330, 410, 330, 461, 331),
    Attack15(23, "Attack", 1, 5, 212, 331, 294, 330, 410, 330, 611, 331),
    Attack16(24, "Attack", 1, 6, 212, 331, 294, 314, 410, 290, 611, 242),
    Attack21(25, "Attack", 2, 1, 361, 331, 410, 300, 525, 216, 611, 155),
    Attack22(26, "Attack", 2, 2, 361, 331, 410, 247, 445, 177, 461, 155),
    Attack23(27, "Attack", 2, 3, 361, 331, 410, 286, 444, 255, 461, 242),
    Attack24(28, "Attack", 2, 4, 361, 331, 410, 330, 452, 330, 461, 331),
    Attack25(29, "Attack", 2, 5, 361, 331, 410, 330, 452, 330, 611, 331),
    Attack26(30, "Attack", 2, 6, 361, 331, 410, 314, 525, 274, 611, 242),
    Attack31(31, "Attack", 3, 1, 361, 242, 410, 225, 525, 186, 611, 155),
    Attack32(32, "Attack", 3, 2, 361, 242, 410, 200, 443, 168, 461, 155),
    Attack33(33, "Attack", 3, 3, 361, 242, 410, 243, 443, 245, 461, 242),
    Attack34(34, "Attack", 3, 4, 361, 242, 410, 280, 448, 320, 461, 331),
    Attack35(35, "Attack", 3, 5, 361, 242, 410, 257, 448, 272, 611, 331),
    Attack36(36, "Attack", 3, 6, 361, 242, 410, 240, 525, 242, 611, 242),
    Attack41(37, "Attack", 4, 1, 361, 155, 410, 157, 451, 155, 611, 155),
    Attack42(38, "Attack", 4, 2, 361, 155, 410, 157, 451, 155, 461, 155),
    Attack43(39, "Attack", 4, 3, 361, 155, 410, 197, 447, 233, 461, 242),
    Attack44(40, "Attack", 4, 4, 361, 155, 410, 235, 447, 307, 461, 331),
    Attack45(41, "Attack", 4, 5, 361, 155, 410, 187, 451, 215, 611, 331),
    Attack46(42, "Attack", 4, 6, 361, 155, 410, 173, 451, 186, 611, 242),
    Attack51(43, "Attack", 5, 1, 212, 155, 294, 155, 410, 155, 611, 155),
    Attack52(44, "Attack", 5, 2, 212, 155, 294, 155, 410, 155, 461, 155),
    Attack53(45, "Attack", 5, 3, 212, 155, 294, 185, 410, 228, 461, 242),
    Attack54(46, "Attack", 5, 4, 212, 155, 294, 208, 410, 292, 461, 331),
    Attack55(47, "Attack", 5, 5, 212, 155, 294, 189, 410, 239, 611, 331),
    Attack56(48, "Attack", 5, 6, 212, 155, 294, 172, 410, 198, 611, 242),
    Attack61(49, "Attack", 6, 1, 212, 242, 294, 227, 410, 200, 611, 155),
    Attack62(50, "Attack", 6, 2, 212, 242, 294, 218, 410, 170, 461, 155),
    Attack63(51, "Attack", 6, 3, 212, 242, 294, 242, 410, 242, 461, 242),
    Attack64(52, "Attack", 6, 4, 212, 242, 294, 269, 410, 312, 461, 331),
    Attack65(53, "Attack", 6, 5, 212, 242, 294, 260, 410, 285, 611, 331),
    Attack66(54, "Attack", 6, 6, 212, 242, 294, 242, 410, 242, 611, 242),
    SetH11(55, "SetH", 1, 1, 212, 331, 212, 331, 212, 331, 212, 331),
    SetM11(56, "SetM", 1, 1, 212, 331, 212, 331, 212, 331, 212, 331),
    SetL11(57, "SetL", 1, 1, 212, 331, 212, 331, 212, 331, 212, 331),
    SetH12(58, "SetH", 1, 2, 212, 331, 411, 309, 430, 320, 395, 331),
    SetM12(59, "SetM", 1, 2, 212, 331, 411, 309, 430, 320, 395, 331),
    SetL12(60, "SetL", 1, 2, 212, 331, 411, 309, 430, 320, 395, 331),
    SetH13(61, "SetH", 1, 3, 212, 331, 411, 284, 430, 262, 395, 240),
    SetM13(62, "SetM", 1, 3, 212, 331, 411, 284, 430, 262, 395, 240),
    SetL13(63, "SetL", 1, 3, 212, 331, 411, 284, 430, 262, 395, 240),
    SetH14(64, "SetH", 1, 4, 212, 331, 411, 200, 430, 175, 395, 150),
    SetM14(65, "SetM", 1, 4, 212, 331, 411, 200, 430, 175, 395, 150),
    SetL14(66, "SetL", 1, 4, 212, 331, 411, 200, 430, 175, 395, 150),
    SetH15(67, "SetH", 1, 5, 212, 331, 411, 274, 430, 212, 285, 155),
    SetM15(68, "SetM", 1, 5, 212, 331, 411, 274, 430, 212, 285, 155),
    SetL15(69, "SetL", 1, 5, 212, 331, 411, 274, 430, 212, 285, 155),
    SetH16(70, "SetH", 1, 6, 212, 331, 411, 312, 430, 290, 285, 242),
    SetM16(71, "SetM", 1, 6, 212, 331, 411, 312, 430, 290, 285, 242),
    SetL16(72, "SetL", 1, 6, 212, 331, 411, 312, 430, 290, 285, 242),
    SetH21(73, "SetH", 2, 1, 361, 331, 430, 300, 360, 309, 285, 331),
    SetM21(74, "SetM", 2, 1, 361, 331, 430, 300, 360, 309, 285, 331),
    SetL21(75, "SetL", 2, 1, 361, 331, 430, 300, 360, 309, 285, 331),
    SetH22(76, "SetH", 2, 2, 361, 331, 361, 331, 361, 331, 361, 331),
    SetM22(77, "SetM", 2, 2, 361, 331, 361, 331, 361, 331, 361, 331),
    SetL22(78, "SetL", 2, 2, 361, 331, 361, 331, 361, 331, 361, 331),
    SetH23(79, "SetH", 2, 3, 361, 331, 411, 313, 430, 285, 395, 242),
    SetM23(80, "SetM", 2, 3, 361, 331, 411, 313, 430, 285, 395, 242),
    SetL23(81, "SetL", 2, 3, 361, 331, 411, 313, 430, 285, 395, 242),
    SetH24(82, "SetH", 2, 4, 361, 331, 411, 266, 430, 208, 395, 155),
    SetM24(83, "SetM", 2, 4, 361, 331, 411, 266, 430, 208, 395, 155),
    SetL24(84, "SetL", 2, 4, 361, 331, 411, 266, 430, 208, 395, 155),
    SetH25(85, "SetH", 2, 5, 361, 331, 411, 300, 430, 250, 285, 155),
    SetM25(86, "SetM", 2, 5, 361, 331, 411, 300, 430, 250, 285, 155),
    SetL25(87, "SetL", 2, 5, 361, 331, 411, 300, 430, 250, 285, 155),
    SetH26(88, "SetH", 2, 6, 361, 331, 411, 320, 430, 290, 285, 242),
    SetM26(89, "SetM", 2, 6, 361, 331, 411, 320, 430, 290, 285, 242),
    SetL26(90, "SetL", 2, 6, 361, 331, 411, 320, 430, 290, 285, 242),
    SetH31(91, "SetH", 3, 1, 361, 242, 411, 265, 430, 295, 285, 331),
    SetM31(92, "SetM", 3, 1, 361, 242, 411, 265, 430, 295, 285, 331),
    SetL31(93, "SetL", 3, 1, 361, 242, 411, 265, 430, 295, 285, 331),
    SetH32(94, "SetH", 3, 2, 361, 242, 411, 265, 430, 300, 395, 331),
    SetM32(95, "SetM", 3, 2, 361, 242, 411, 265, 430, 300, 395, 331),
    SetL32(96, "SetL", 3, 2, 361, 242, 411, 265, 430, 300, 395, 331),
    SetH33(97, "SetH", 3, 3, 361, 242, 361, 242, 361, 242, 361, 242),
    SetM33(98, "SetM", 3, 3, 361, 242, 361, 242, 361, 242, 361, 242),
    SetL33(99, "SetL", 3, 3, 361, 242, 361, 242, 361, 242, 361, 242),
    SetH34(100, "SetH", 3, 4, 361, 242, 411, 215, 430, 190, 395, 155),
    SetM34(101, "SetM", 3, 4, 361, 242, 411, 215, 430, 190, 395, 155),
    SetL34(102, "SetL", 3, 4, 361, 242, 411, 215, 430, 190, 395, 155),
    SetH35(103, "SetH", 3, 5, 361, 242, 411, 225, 430, 200, 285, 155),
    SetM35(104, "SetM", 3, 5, 361, 242, 411, 225, 430, 200, 285, 155),
    SetL35(105, "SetL", 3, 5, 361, 242, 411, 225, 430, 200, 285, 155),
    SetH36(106, "SetH", 3, 6, 361, 242, 411, 236, 430, 218, 285, 242),
    SetM36(107, "SetM", 3, 6, 361, 242, 411, 236, 430, 218, 285, 242),
    SetL36(108, "SetL", 3, 6, 361, 242, 411, 236, 430, 218, 285, 242),
    SetH41(109, "SetH", 4, 1, 361, 155, 411, 164, 430, 190, 285, 331),
    SetM41(110, "SetM", 4, 1, 361, 155, 411, 164, 430, 190, 285, 331),
    SetL41(111, "SetL", 4, 1, 361, 155, 411, 164, 430, 190, 285, 331),
    SetH42(112, "SetH", 4, 2, 361, 155, 411, 200, 430, 262, 395, 331),
    SetM42(113, "SetM", 4, 2, 361, 155, 411, 200, 430, 262, 395, 331),
    SetL42(114, "SetL", 4, 2, 361, 155, 411, 200, 430, 262, 395, 331),
    SetH43(115, "SetH", 4, 3, 361, 155, 411, 185, 430, 210, 395, 242),
    SetM43(116, "SetM", 4, 3, 361, 155, 411, 185, 430, 210, 395, 242),
    SetL43(117, "SetL", 4, 3, 361, 155, 411, 185, 430, 210, 395, 242),
    SetH44(118, "SetH", 4, 4, 361, 155, 361, 155, 361, 155, 361, 155),
    SetM44(119, "SetM", 4, 4, 361, 155, 361, 155, 361, 155, 361, 155),
    SetL44(120, "SetL", 4, 4, 361, 155, 361, 155, 361, 155, 361, 155),
    SetH45(121, "SetH", 4, 5, 361, 155, 411, 165, 430, 190, 285, 155),
    SetM45(122, "SetM", 4, 5, 361, 155, 411, 165, 430, 190, 285, 155),
    SetL45(123, "SetL", 4, 5, 361, 155, 411, 165, 430, 190, 285, 155),
    SetH46(124, "SetH", 4, 6, 361, 155, 411, 178, 430, 200, 285, 242),
    SetM46(125, "SetM", 4, 6, 361, 155, 411, 178, 430, 200, 285, 242),
    SetL46(126, "SetL", 4, 6, 361, 155, 411, 178, 430, 200, 285, 242),
    SetH51(127, "SetH", 5, 1, 212, 155, 411, 220, 430, 260, 285, 331),
    SetM51(128, "SetM", 5, 1, 212, 155, 411, 220, 430, 260, 285, 331),
    SetL51(129, "SetL", 5, 1, 212, 155, 411, 220, 430, 260, 285, 331),
    SetH52(130, "SetH", 5, 2, 212, 155, 411, 240, 430, 300, 395, 331),
    SetM52(131, "SetM", 5, 2, 212, 155, 411, 240, 430, 300, 395, 331),
    SetL52(132, "SetL", 5, 2, 212, 155, 411, 240, 430, 300, 395, 331),
    SetH53(133, "SetH", 5, 3, 212, 155, 411, 200, 430, 220, 395, 242),
    SetM53(134, "SetM", 5, 3, 212, 155, 411, 200, 430, 220, 395, 242),
    SetL53(135, "SetL", 5, 3, 212, 155, 411, 200, 430, 220, 395, 242),
    SetH54(136, "SetH", 5, 4, 212, 155, 411, 190, 430, 162, 395, 155),
    SetM54(137, "SetM", 5, 4, 212, 155, 411, 190, 430, 162, 395, 155),
    SetL54(138, "SetL", 5, 4, 212, 155, 411, 190, 430, 162, 395, 155),
    SetH55(139, "SetH", 5, 5, 212, 155, 212, 155, 212, 155, 212, 155),
    SetM55(140, "SetM", 5, 5, 212, 155, 212, 155, 212, 155, 212, 155),
    SetL55(141, "SetL", 5, 5, 212, 155, 212, 155, 212, 155, 212, 155),
    SetH56(142, "SetH", 5, 6, 212, 155, 411, 180, 430, 210, 285, 242),
    SetM56(143, "SetM", 5, 6, 212, 155, 411, 180, 430, 210, 285, 242),
    SetL56(144, "SetL", 5, 6, 212, 155, 411, 180, 430, 210, 285, 242),
    SetH61(145, "SetH", 6, 1, 212, 242, 411, 260, 430, 296, 285, 331),
    SetM61(146, "SetM", 6, 1, 212, 242, 411, 260, 430, 296, 285, 331),
    SetL61(147, "SetL", 6, 1, 212, 242, 411, 260, 430, 296, 285, 331),
    SetH62(148, "SetH", 6, 2, 212, 242, 411, 267, 430, 310, 395, 331),
    SetM62(149, "SetM", 6, 2, 212, 242, 411, 267, 430, 310, 395, 331),
    SetL62(150, "SetL", 6, 2, 212, 242, 411, 267, 430, 310, 395, 331),
    SetH63(151, "SetH", 6, 3, 212, 242, 411, 210, 430, 230, 395, 242),
    SetM63(152, "SetM", 6, 3, 212, 242, 411, 210, 430, 230, 395, 242),
    SetL63(153, "SetL", 6, 3, 212, 242, 411, 210, 430, 230, 395, 242),
    SetH64(154, "SetH", 6, 4, 212, 242, 411, 206, 430, 165, 395, 155),
    SetM64(155, "SetM", 6, 4, 212, 242, 411, 206, 430, 165, 395, 155),
    SetL64(156, "SetL", 6, 4, 212, 242, 411, 206, 430, 165, 395, 155),
    SetH65(157, "SetH", 6, 5, 212, 242, 411, 220, 430, 187, 285, 155),
    SetM65(158, "SetM", 6, 5, 212, 242, 411, 220, 430, 187, 285, 155),
    SetL65(159, "SetL", 6, 5, 212, 242, 411, 220, 430, 187, 285, 155),
    SetH66(160, "SetH", 6, 6, 212, 242, 212, 242, 212, 242, 212, 242),
    SetM66(161, "SetM", 6, 6, 212, 242, 212, 242, 212, 242, 212, 242),
    SetL66(162, "SetL", 6, 6, 212, 242, 212, 242, 212, 242, 212, 242),
    Reception11(163, "Reception", 1, 1, 212, 331, 212, 331, 212, 331, 212, 331),
    Reception12(164, "Reception", 1, 2, 212, 331, 270, 331, 319, 331, 361, 331),
    Reception13(165, "Reception", 1, 3, 212, 331, 274, 292, 325, 256, 361, 242),
    Reception14(166, "Reception", 1, 4, 212, 331, 256, 282, 317, 205, 361, 155),
    Reception15(167, "Reception", 1, 5, 212, 331, 212, 284, 212, 190, 212, 155),
    Reception16(168, "Reception", 1, 6, 212, 331, 212, 284, 212, 264, 212, 242),
    Reception21(169, "Reception", 2, 1, 361, 331, 307, 331, 260, 331, 212, 331),
    Reception22(170, "Reception", 2, 2, 361, 331, 361, 331, 361, 331, 361, 331),
    Reception23(171, "Reception", 2, 3, 361, 331, 361, 298, 361, 297, 361, 242),
    Reception24(172, "Reception", 2, 4, 361, 331, 361, 298, 361, 196, 361, 155),
    Reception25(173, "Reception", 2, 5, 361, 331, 314, 276, 259, 209, 212, 155),
    Reception26(174, "Reception", 2, 6, 361, 331, 293, 292, 250, 265, 212, 242),
    Reception31(175, "Reception", 3, 1, 361, 242, 294, 281, 255, 307, 212, 331),
    Reception32(176, "Reception", 3, 2, 361, 242, 361, 266, 361, 303, 361, 331),
    Reception33(177, "Reception", 3, 3, 361, 242, 361, 242, 361, 242, 361, 242),
    Reception34(178, "Reception", 3, 4, 361, 242, 361, 217, 361, 173, 361, 155),
    Reception35(179, "Reception", 3, 5, 361, 242, 303, 211, 251, 176, 212, 155),
    Reception36(180, "Reception", 3, 6, 361, 242, 294, 246, 248, 245, 212, 242),
    Reception41(181, "Reception", 4, 1, 361, 155, 315, 211, 266, 273, 212, 331),
    Reception42(182, "Reception", 4, 2, 361, 155, 361, 198, 361, 290, 361, 331),
    Reception43(183, "Reception", 4, 3, 361, 155, 361, 198, 361, 220, 361, 242),
    Reception44(184, "Reception", 4, 4, 361, 155, 361, 155, 361, 155, 361, 155),
    Reception45(185, "Reception", 4, 5, 361, 155, 294, 160, 253, 155, 212, 155),
    Reception46(186, "Reception", 4, 6, 361, 155, 294, 197, 252, 221, 212, 242),
    Reception51(187, "Reception", 5, 1, 212, 155, 212, 210, 212, 280, 212, 331),
    Reception52(188, "Reception", 5, 2, 212, 155, 267, 217, 323, 285, 361, 331),
    Reception53(189, "Reception", 5, 3, 212, 155, 280, 196, 316, 216, 361, 242),
    Reception54(190, "Reception", 5, 4, 212, 155, 270, 156, 320, 155, 361, 155),
    Reception55(191, "Reception", 5, 5, 212, 155, 212, 155, 212, 155, 212, 155),
    Reception56(192, "Reception", 5, 6, 212, 155, 212, 177, 212, 220, 212, 242),
    Reception61(193, "Reception", 6, 1, 212, 242, 212, 270, 212, 306, 212, 331),
    Reception62(194, "Reception", 6, 2, 212, 242, 261, 272, 314, 303, 361, 331),
    Reception63(195, "Reception", 6, 3, 212, 242, 268, 242, 319, 242, 361, 242),
    Reception64(196, "Reception", 6, 4, 212, 242, 287, 197, 316, 176, 361, 155),
    Reception65(197, "Reception", 6, 5, 212, 242, 212, 200, 212, 175, 212, 155),
    Reception66(198, "Reception", 6, 6, 212, 242, 212, 242, 212, 242, 212, 242),
    Defence11(199, "Defence", 1, 1, 212, 331, 212, 331, 212, 331, 212, 331),
    Defence12(200, "Defence", 1, 2, 212, 331, 270, 331, 319, 331, 361, 331),
    Defence13(201, "Defence", 1, 3, 212, 331, 274, 292, 325, 256, 361, 242),
    Defence14(202, "Defence", 1, 4, 212, 331, 256, 282, 317, 205, 361, 155),
    Defence15(203, "Defence", 1, 5, 212, 331, 212, 284, 212, 190, 212, 155),
    Defence16(204, "Defence", 1, 6, 212, 331, 212, 284, 212, 264, 212, 242),
    Defence21(205, "Defence", 2, 1, 361, 331, 307, 331, 260, 331, 212, 331),
    Defence22(206, "Defence", 2, 2, 361, 331, 361, 331, 361, 331, 361, 331),
    Defence23(207, "Defence", 2, 3, 361, 331, 361, 298, 361, 297, 361, 242),
    Defence24(208, "Defence", 2, 4, 361, 331, 361, 298, 361, 196, 361, 155),
    Defence25(209, "Defence", 2, 5, 361, 331, 314, 276, 259, 209, 212, 155),
    Defence26(210, "Defence", 2, 6, 361, 331, 293, 292, 250, 265, 212, 242),
    Defence31(211, "Defence", 3, 1, 361, 242, 294, 281, 255, 307, 212, 331),
    Defence32(212, "Defence", 3, 2, 361, 242, 361, 266, 361, 303, 361, 331),
    Defence33(213, "Defence", 3, 3, 361, 242, 361, 242, 361, 242, 361, 242),
    Defence34(214, "Defence", 3, 4, 361, 242, 361, 217, 361, 173, 361, 155),
    Defence35(215, "Defence", 3, 5, 361, 242, 303, 211, 251, 176, 212, 155),
    Defence36(216, "Defence", 3, 6, 361, 242, 294, 246, 248, 245, 212, 242),
    Defence41(217, "Defence", 4, 1, 361, 155, 315, 211, 266, 273, 212, 331),
    Defence42(218, "Defence", 4, 2, 361, 155, 361, 198, 361, 290, 361, 331),
    Defence43(219, "Defence", 4, 3, 361, 155, 361, 198, 361, 220, 361, 242),
    Defence44(220, "Defence", 4, 4, 361, 155, 361, 155, 361, 155, 361, 155),
    Defence45(221, "Defence", 4, 5, 361, 155, 294, 160, 253, 155, 212, 155),
    Defence46(222, "Defence", 4, 6, 361, 155, 294, 197, 252, 221, 212, 242),
    Defence51(223, "Defence", 5, 1, 212, 155, 212, 210, 212, 280, 212, 331),
    Defence52(224, "Defence", 5, 2, 212, 155, 267, 217, 323, 285, 361, 331),
    Defence53(225, "Defence", 5, 3, 212, 155, 280, 196, 316, 216, 361, 242),
    Defence54(226, "Defence", 5, 4, 212, 155, 270, 156, 320, 155, 361, 155),
    Defence55(227, "Defence", 5, 5, 212, 155, 212, 155, 212, 155, 212, 155),
    Defence56(228, "Defence", 5, 6, 212, 155, 212, 177, 212, 220, 212, 242),
    Defence61(229, "Defence", 6, 1, 212, 242, 212, 270, 212, 306, 212, 331),
    Defence62(230, "Defence", 6, 2, 212, 242, 261, 272, 314, 303, 361, 331),
    Defence63(231, "Defence", 6, 3, 212, 242, 268, 242, 319, 242, 361, 242),
    Defence64(232, "Defence", 6, 4, 212, 242, 287, 197, 316, 176, 361, 155),
    Defence65(233, "Defence", 6, 5, 212, 242, 212, 200, 212, 175, 212, 155),
    Defence66(234, "Defence", 6, 6, 212, 242, 212, 242, 212, 242, 212, 242),
    BlockAttack12(235, "BlockAttack", 1, 2, 611, 155, 611, 155, 361, 331, 361, 331),
    BlockAttack13(236, "BlockAttack", 1, 3, 611, 155, 611, 155, 361, 242, 361, 242),
    BlockAttack14(237, "BlockAttack", 1, 4, 611, 155, 611, 155, 361, 155, 361, 155),
    BlockAttack22(238, "BlockAttack", 2, 2, 461, 155, 461, 155, 361, 331, 361, 331),
    BlockAttack23(239, "BlockAttack", 2, 3, 461, 155, 461, 155, 361, 242, 361, 242),
    BlockAttack24(240, "BlockAttack", 2, 4, 461, 155, 461, 155, 361, 155, 361, 155),
    BlockAttack32(241, "BlockAttack", 3, 2, 461, 242, 461, 242, 361, 331, 361, 331),
    BlockAttack33(242, "BlockAttack", 3, 3, 461, 242, 461, 242, 361, 242, 361, 242),
    BlockAttack34(243, "BlockAttack", 3, 4, 461, 242, 461, 242, 361, 155, 361, 155),
    BlockAttack42(244, "BlockAttack", 4, 2, 461, 331, 461, 331, 361, 331, 361, 331),
    BlockAttack43(245, "BlockAttack", 4, 3, 461, 331, 461, 331, 361, 242, 361, 242),
    BlockAttack44(246, "BlockAttack", 4, 4, 461, 331, 461, 331, 361, 155, 361, 155),
    BlockAttack52(247, "BlockAttack", 5, 2, 611, 331, 611, 331, 361, 331, 361, 331),
    BlockAttack53(248, "BlockAttack", 5, 3, 611, 331, 611, 331, 361, 242, 361, 242),
    BlockAttack54(249, "BlockAttack", 5, 4, 611, 331, 611, 331, 361, 155, 361, 155),
    BlockAttack62(250, "BlockAttack", 6, 2, 611, 242, 611, 242, 361, 331, 361, 331),
    BlockAttack63(251, "BlockAttack", 6, 3, 611, 242, 611, 242, 361, 242, 361, 242),
    BlockAttack64(252, "BlockAttack", 6, 4, 611, 242, 611, 242, 361, 155, 361, 155),
    BlockRH21(253, "BlockRH", 2, 1, 361, 331, 361, 331, 212, 331, 212, 331),
    BlockRH22(254, "BlockRH", 2, 2, 361, 331, 361, 331, 361, 331, 361, 331),
    BlockRH23(255, "BlockRH", 2, 3, 361, 331, 361, 331, 361, 242, 361, 242),
    BlockRH24(256, "BlockRH", 2, 4, 361, 331, 361, 331, 361, 155, 361, 155),
    BlockRH25(257, "BlockRH", 2, 5, 361, 331, 361, 331, 212, 155, 212, 155),
    BlockRH26(258, "BlockRH", 2, 6, 361, 331, 361, 331, 212, 242, 212, 242),
    BlockRH27(259, "BlockRH", 2, 7, 361, 331, 361, 331, 295, 80, 295, 80),
    BlockRH28(260, "BlockRH", 2, 8, 361, 331, 361, 331, 295, 405, 295, 405),
    BlockRH29(261, "BlockRH", 2, 9, 361, 331, 361, 331, 55, 242, 55, 242),
    BlockRH31(262, "BlockRH", 3, 1, 361, 242, 361, 242, 212, 331, 212, 331),
    BlockRH32(263, "BlockRH", 3, 2, 361, 242, 361, 242, 361, 331, 361, 331),
    BlockRH33(264, "BlockRH", 3, 3, 361, 242, 361, 242, 361, 242, 361, 242),
    BlockRH34(265, "BlockRH", 3, 4, 361, 242, 361, 242, 361, 155, 361, 155),
    BlockRH35(266, "BlockRH", 3, 5, 361, 242, 361, 242, 212, 155, 212, 155),
    BlockRH36(267, "BlockRH", 3, 6, 361, 242, 361, 242, 212, 242, 212, 242),
    BlockRH37(268, "BlockRH", 3, 7, 361, 242, 361, 242, 295, 80, 295, 80),
    BlockRH38(269, "BlockRH", 3, 8, 361, 242, 361, 242, 295, 405, 295, 405),
    BlockRH39(270, "BlockRH", 3, 9, 361, 242, 361, 242, 55, 242, 55, 242),
    BlockRH41(271, "BlockRH", 4, 1, 361, 155, 361, 155, 212, 331, 212, 331),
    BlockRH42(272, "BlockRH", 4, 2, 361, 155, 361, 155, 361, 331, 361, 331),
    BlockRH43(273, "BlockRH", 4, 3, 361, 155, 361, 155, 361, 242, 361, 242),
    BlockRH44(274, "BlockRH", 4, 4, 361, 155, 361, 155, 361, 155, 361, 155),
    BlockRH45(275, "BlockRH", 4, 5, 361, 155, 361, 155, 212, 155, 212, 155),
    BlockRH46(276, "BlockRH", 4, 6, 361, 155, 361, 155, 212, 242, 212, 242),
    BlockRH47(277, "BlockRH", 4, 7, 361, 155, 361, 155, 295, 80, 295, 80),
    BlockRH48(278, "BlockRH", 4, 8, 361, 155, 361, 155, 295, 405, 295, 405),
    BlockRH49(279, "BlockRH", 4, 9, 361, 155, 361, 155, 55, 242, 55, 242),
    BlockRO21(280, "BlockRO", 2, 1, 361, 331, 361, 331, 611, 155, 611, 155),
    BlockRO22(281, "BlockRO", 2, 2, 361, 331, 361, 331, 461, 155, 461, 155),
    BlockRO23(282, "BlockRO", 2, 3, 361, 331, 361, 331, 461, 242, 461, 242),
    BlockRO24(283, "BlockRO", 2, 4, 361, 331, 361, 331, 461, 331, 461, 331),
    BlockRO25(284, "BlockRO", 2, 5, 361, 331, 361, 331, 611, 331, 611, 331),
    BlockRO26(285, "BlockRO", 2, 6, 361, 331, 361, 331, 611, 242, 611, 242),
    BlockRO27(286, "BlockRO", 2, 7, 361, 331, 361, 331, 525, 405, 525, 405),
    BlockRO28(287, "BlockRO", 2, 8, 361, 331, 361, 331, 525, 80, 525, 80),
    BlockRO29(288, "BlockRO", 2, 9, 361, 331, 361, 331, 745, 242, 745, 242),
    BlockRO31(289, "BlockRO", 3, 1, 361, 242, 361, 242, 611, 155, 611, 155),
    BlockRO32(290, "BlockRO", 3, 2, 361, 242, 361, 242, 461, 155, 461, 155),
    BlockRO33(291, "BlockRO", 3, 3, 361, 242, 361, 242, 461, 242, 461, 242),
    BlockRO34(292, "BlockRO", 3, 4, 361, 242, 361, 242, 461, 331, 461, 331),
    BlockRO35(293, "BlockRO", 3, 5, 361, 242, 361, 242, 611, 331, 611, 331),
    BlockRO36(294, "BlockRO", 3, 6, 361, 242, 361, 242, 611, 242, 611, 242),
    BlockRO37(295, "BlockRO", 3, 7, 361, 242, 361, 242, 525, 405, 525, 405),
    BlockRO38(296, "BlockRO", 3, 8, 361, 242, 361, 242, 525, 80, 525, 80),
    BlockRO39(297, "BlockRO", 3, 9, 361, 242, 361, 242, 745, 242, 745, 242),
    BlockRO41(298, "BlockRO", 4, 1, 361, 155, 361, 155, 611, 155, 611, 155),
    BlockRO42(299, "BlockRO", 4, 2, 361, 155, 361, 155, 461, 155, 461, 155),
    BlockRO43(300, "BlockRO", 4, 3, 361, 155, 361, 155, 461, 242, 461, 242),
    BlockRO44(301, "BlockRO", 4, 4, 361, 155, 361, 155, 461, 331, 461, 331),
    BlockRO45(302, "BlockRO", 4, 5, 361, 155, 361, 155, 611, 331, 611, 331),
    BlockRO46(303, "BlockRO", 4, 6, 361, 155, 361, 155, 611, 242, 611, 242),
    BlockRO47(304, "BlockRO", 4, 7, 361, 155, 361, 155, 525, 405, 525, 405),
    BlockRO48(305, "BlockRO", 4, 8, 361, 155, 361, 155, 525, 80, 525, 80),
    BlockRO49(306, "BlockRO", 4, 9, 361, 155, 361, 155, 745, 242, 745, 242);

    int id;
    String skill;
    int from;
    int to;
    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;
    int x4;
    int y4;

    private VollyCourtCoordinate(int id, String skill, int from, int to, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.id = id;
        this.skill = skill;
        this.from = from;
        this.to = to;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public int getX4() {
        return x4;
    }

    public void setX4(int x4) {
        this.x4 = x4;
    }

    public int getY4() {
        return y4;
    }

    public void setY4(int y4) {
        this.y4 = y4;
    }

}
