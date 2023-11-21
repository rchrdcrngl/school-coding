import 'dart:convert';

import 'package:fap_g6/order_confirmation.dart';
import 'package:fap_g6/store/components/product_tile.dart';
import 'package:fap_g6/store/product_item.dart';
import 'package:flutter/material.dart';
import 'package:fap_g6/app_title.dart';
import 'package:http/http.dart' as http;

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final List<Product> cartProducts = [];

  @override
  void initState() {
    super.initState();
  }

  Future<List<Product>> getProducts() async {
    //Get product details from product_item.dart
    List<Product> products = Product.productList;
    return products;
  }

  void addToCart(Product p){
    setState(() {
      cartProducts.add(p);
    });
  }

  void removeCartItem(int index){
    setState(() {
      cartProducts.removeAt(index);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Header(headerText: 'TigerHub Madness Store')
        ),
      floatingActionButton: FloatingActionButton.extended(
          onPressed: (){
            Navigator.push(context, MaterialPageRoute(builder: (context) => OrderConPage(cartItems: cartProducts, removeCartItem: removeCartItem,)));
          },
          backgroundColor: Colors.green,
          label: Text('Items: ${cartProducts.length}'),
          icon: const Icon(Icons.shopping_cart),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
              margin: const EdgeInsets.symmetric(vertical: 12, horizontal: 8),
              child: const Text("Click on a product to add to your cart.")
          ),
          Expanded(
            child: FutureBuilder<List<Product>>(
                future: getProducts(),
                builder: (context,snapshot){
                  if (snapshot.hasData &&
                  snapshot.connectionState == ConnectionState.done) {
                    return ListView.builder(
                      itemCount: snapshot.data?.length,
                      itemBuilder: (context, index) =>
                          ProductTile(item: snapshot.data![index], add: addToCart),
                    );
                  } else {
                    return const Center(child: CircularProgressIndicator());
                  }
                }
            ),
          ),
        ],
      ),
    );
  }
}