import 'package:fap_g6/checkout.dart';
import 'package:flutter/material.dart';
import 'package:fap_g6/store/product_item.dart';
import 'package:fap_g6/store/components/product_tile.dart';
import 'package:fap_g6/app_title.dart';
import 'package:fap_g6/home_page.dart';
import 'package:get/get.dart';


class OrderConPage extends StatefulWidget {
  final List<Product> cartItems;
  final Function removeCartItem;
  const OrderConPage({Key? key, required this.cartItems, required this.removeCartItem}) : super(key: key);

  @override
  State<OrderConPage> createState() => _OrderConPageState();
}

class _OrderConPageState extends State<OrderConPage> {
  double total = 0.0;

  @override
  void initState(){
    super.initState();
    for(Product p in widget.cartItems){
      total += p.itemPrice;
    }
  }

  void removeItem(Product p, int index){
    total -= p.itemPrice;
    setState(() {
      widget.removeCartItem(index);
    });
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Header(headerText: 'Order Confirmation'),
      ),
      floatingActionButton: widget.cartItems.isEmpty? const SizedBox(): FloatingActionButton.extended(
        onPressed: (){
          //Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => CheckoutPage(total: total, orderItems: widget.cartItems)));
          Get.offAll(CheckoutPage(total: total, orderItems: widget.cartItems));
        },
        backgroundColor: Colors.green,
        label: const Text('Continue to Checkout'),
        icon: const Icon(Icons.shopping_cart),
      ),
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Container(
                margin: const EdgeInsets.symmetric(vertical: 12, horizontal: 8),
                child: const Text("Please review your order before confirming.")
            ),
            Expanded(
              child: ListView.builder(
                itemCount: widget.cartItems.length,
                itemBuilder: (context, index) =>
                    ProductTile(item: widget.cartItems[index],deleteIndex: index, delete: (){removeItem(widget.cartItems[index], index);}),
              ),
            ),
            Container(
                height: 40,
                color: Colors.blueGrey.withOpacity(0.5),
                padding: const EdgeInsets.only(left:10),
                child: Align(
                  alignment: Alignment.centerLeft,
                    child: Text("Total: Php. $total", style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),))
            ),

          ],
        ),
      ),
    );
  }
}


