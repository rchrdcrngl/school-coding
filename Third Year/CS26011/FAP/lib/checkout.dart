import 'package:fap_g6/welcome_page.dart';
import 'package:flutter/material.dart';
import 'package:fap_g6/app_title.dart';
import 'package:fap_g6/store/product_item.dart';
import 'package:fap_g6/store/components/product_tile.dart';
import 'package:fap_g6/home_page.dart';
import 'package:get/get.dart';
import 'package:qr_flutter/qr_flutter.dart';


class CheckoutPage extends StatefulWidget {

  final double total;
  final List<Product> orderItems;
  const CheckoutPage({Key? key, required this.total, required this.orderItems}) : super(key: key);

  @override
  State<CheckoutPage> createState() => _CheckoutPageState();
}


class _CheckoutPageState extends State<CheckoutPage> {
  StringBuffer orderList = StringBuffer();

  @override
  void initState(){
    super.initState();
    listToJSON();
  }

  void listToJSON(){
    orderList.write('{"items":[');
    for(int i = 0; i<widget.orderItems.length; i++){

      if (i+1 != widget.orderItems.length ) {
        orderList.write('{"name":"${widget.orderItems[i].itemName}","price":"${widget.orderItems[i].itemPrice}"},');
      } else {
        orderList.write('{"name":"${widget.orderItems[i].itemName}","price":"${widget.orderItems[i].itemPrice}"}');
      }
    }
    orderList.write('],"total":"${widget.total}"}');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Header(headerText: 'Checkout'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
                margin: const EdgeInsets.symmetric(vertical: 12, horizontal: 8),
                child: const Text("Thank you for shopping at THM!",
                  style: TextStyle(
                    color: Color(0XFF1378EC),
                    fontSize: 21,
                  ),),
            ),
            Container(
                margin: const EdgeInsets.symmetric(vertical: 12, horizontal: 8),
                child: const Text("Your order has been processed.")
            ),
            Container(
              decoration: BoxDecoration(
                color: Color(0XFF57C1D8),
                border: Border.all(
                  width: 5,
                ),
                borderRadius: BorderRadius.circular(12),
              ),
              child: QrImage(
                data: orderList.toString(),
                version: QrVersions.auto,
                size: 200.0,
              ),
            ),
            const SizedBox(height: 3,),
            const Text("Please scan this QR Code for your receipt."),
            const SizedBox(height: 10,),
            Text("Order Total: Php. ${widget.total}",
            style: const TextStyle(
              color: Color(0XFF1378EC),
              fontSize: 21,
            ),),
            const SizedBox(height: 10,),
            ElevatedButton(onPressed: ()=>Get.offAll(HomePage()), style:ElevatedButton.styleFrom(primary: Colors.green), child: const Text('Create new order'))
          ],
        ),
      ),
    );
  }
}


