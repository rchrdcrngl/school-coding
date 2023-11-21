//CARINGAL, Richard Maru A.
//3CSC
//ICS26011 - MP1

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Home Page',
      theme: ThemeData(
        colorScheme: ColorScheme.dark(),
        useMaterial3: true,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //Set the title of the app bar to bold and a font size of 20
      appBar: AppBar(
        title: Text(
          "RICHARD'S 1ST FLUTTER APP",
          style: const TextStyle(
              fontSize: 20, fontWeight: FontWeight.bold, letterSpacing: 2),
        ),
      ),

      //Insert my own picture and my name in the body of the app
      body: Column(
        children: [
          Center(
            child: Image.asset('assets/picture.jpg'),
          ),
          Text(
            "Richard Maru Caringal",
            style: const TextStyle(
              height: 3,
              fontSize: 20,
              fontStyle: FontStyle.italic,
              color: Colors.white,
            ),
          ),
          Text(
            "3CSC",
            style: const TextStyle(
              fontSize: 13,
              color: Colors.lightBlueAccent,
            ),
          ),
        ],
      ),
    );
  }
}
