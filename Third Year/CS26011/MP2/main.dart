import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mi Card',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromRGBO(22, 44, 58, 100),
      body: Center(
        child: Container(
          margin: const EdgeInsets.symmetric(vertical:0, horizontal:30.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: const <Widget>[
              CircleAvatar(
                maxRadius: 80.0,
                backgroundImage: AssetImage("images/picture.jpg"),
              ),
              SizedBox(
                height:10.0,
              ),
              Text("Richard Maru Caringal",
                style: TextStyle(
                  fontSize: 30,
                  fontFamily: "YesevaOne",
                  color: Colors.amberAccent,
                ),
              ),
              SizedBox(
                height:5.0,
              ),
              Text("Flutter Developer",
                style: TextStyle(
                  fontSize: 16,
                  fontFamily: "Lato",
                  color: Colors.white,
                ),
              ),
              SizedBox(
                height: 50.0,
                width:150.0,
                child: Divider(
                  height:1,
                  thickness: 2,
                  color: Colors.amberAccent
                ),
              ),
              Card(
                color: Color.fromRGBO(96, 91, 117, 100),
                child: ListTile(
                  leading: Icon(Icons.email, color: Color.fromRGBO(221, 215, 245,100),),
                  title: Text("richardmaru.caringal.cics@ust.edu.ph", style: TextStyle(fontFamily: "Lato", fontSize: 16, color: Colors.white),),
                ),
              ),
              SizedBox(
                height:10.0,
              ),
              Card(
                color: Color.fromRGBO(96, 91, 117, 100),
                child: ListTile(
                  leading: Icon(Icons.phone, color: Color.fromRGBO(221, 215, 245,100),),
                  title: Text("+63 956 078 7109", style: TextStyle(fontFamily: "Lato", fontSize: 16, color: Colors.white),),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
