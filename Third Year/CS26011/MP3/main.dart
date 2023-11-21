import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Rock-Paper-Scissors',
      theme: ThemeData(
        primarySwatch: Colors.orange,
        colorScheme: const ColorScheme.dark(),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Rock-Paper-Scissors'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final _random = Random();
  Color bgColor = Colors.blueGrey;
  String strStatus = 'Press left or right \nbutton to start';
  int leftImgNum = 0;
  int rightImgNum = 0;


  void throwHand (){
    setState(() {
      //Randomize pictures
      leftImgNum = _random.nextInt(3);
      rightImgNum = _random.nextInt(3);
      //If round is draw, set BG Color to yellow
      if(leftImgNum==rightImgNum) {
        bgColor = Colors.yellow;
        strStatus = 'It\'s a draw!';
      }else{
        //Else set it to black
        bgColor = Colors.blueGrey;
        strStatus = 'Who won?';
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title,),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Expanded(
                flex: 2,
                child: Center(child: Text(strStatus, style: const TextStyle(fontFamily: 'FredokaOne', fontSize: 40),))),
            Expanded(
              flex: 6,
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 15.0),
                child: Row(
                  children: [
                    Expanded(
                      child: ConstrainedBox(
                        constraints: const BoxConstraints.expand(),
                        child: TextButton(
                          //Apply styles to have circular button
                          style: TextButton.styleFrom(
                            backgroundColor: bgColor,
                            shape: const CircleBorder(),
                            padding: const EdgeInsets.all(15),
                          ),
                          onPressed: throwHand,
                          child: Image.asset('assets/images/$leftImgNum.png'),
                        ),
                      ),
                    ),
                    const SizedBox(width: 10.0,),
                    Expanded(
                      child: ConstrainedBox(
                        constraints: const BoxConstraints.expand(),
                        child: TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: bgColor,
                            shape: const CircleBorder(),
                            padding: const EdgeInsets.all(15),
                          ),
                          onPressed: throwHand,
                          //Transform to mirror images
                          child: Transform.scale(
                              scaleX: -1,
                              child: Image.asset('assets/images/$rightImgNum.png')),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
