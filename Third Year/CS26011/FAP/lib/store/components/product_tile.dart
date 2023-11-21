
import 'package:flutter/material.dart';
import '../product_item.dart';

const kProdTileMargin = 5.0;
const kProdTileBorderRadius = 7.0;
const kProdTileActiveColor = Colors.white70;
const kProdTileInactiveColor = Colors.grey;

Widget ProductTile({required Product item, Function? add, Function? delete, int? deleteIndex}){
  bool isActive = stockCheck(item.itemStock);
  return Container(
    margin: const EdgeInsets.all(kProdTileMargin),
    decoration: BoxDecoration(
        color: stockCheckColor(item.itemStock),
        borderRadius: BorderRadius.circular(kProdTileBorderRadius),
      boxShadow: [
        BoxShadow(
          color: Colors.grey.withOpacity(0.25),
          spreadRadius: 2,
          blurRadius: 2,
          offset: const Offset(0, 1), // changes position of shadow
        ),
      ],
    ),
    child: ListTile(
      contentPadding: const EdgeInsets.symmetric(horizontal: 12, vertical: 12),
      leading: AspectRatio(
          aspectRatio: 1,
          child: Image.network(item.url)),
      title: Text(item.itemName),
      subtitle: Text('Php. ${item.itemPrice} \nIn Stock: ${item.itemStock} \n${item.itemDesc}'),
      trailing: GestureDetector(
        onTap: (){
          if(isActive && add != null) add(item);
          if(isActive && delete != null) delete();
        },
        child: isActive? delete != null? const Icon(Icons.delete): const Icon(Icons.add) : const FittedBox()
      ),
    ),
  );
}


//Pag wala na stock, grey bg
stockCheckColor(int stock){
  if(stock < 1) {
    return kProdTileInactiveColor.withOpacity(0.25);
  }
  else{
    return kProdTileActiveColor;
  }
}

stockCheck(int stock){
  return stock >= 1;
}

