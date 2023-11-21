import 'package:flutter/material.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';
import 'welcome_page.dart';


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'TigerHub Madness Store',
      theme: ThemeData(
        primaryColor: Colors.white,
        scaffoldBackgroundColor: const Color(0XFFE8DFDF),
        fontFamily: 'Inter',
        appBarTheme: const AppBarTheme(backgroundColor: Color(0XFF278CAA), foregroundColor: Colors.white, elevation: 0),
      ),
      debugShowCheckedModeBanner: false,
      home: const WelcomePage(),
    );
  }
}



