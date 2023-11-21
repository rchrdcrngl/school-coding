import 'package:fap_g6/const.dart';
import 'package:flutter/material.dart';

Widget Header({required String headerText }){
  return Row(
    children: [
      Image.asset(
        headerURL,
        fit: BoxFit.contain,
        height: 50,
        color: Colors.white,
      ),
      const SizedBox(width: 10,),
      Text(headerText, style: const TextStyle(fontWeight: FontWeight.bold),)
    ],
  );
}