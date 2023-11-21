import 'package:audioplayers/audioplayers.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(PianoApp());
}

class PianoApp extends StatefulWidget {
  const PianoApp({Key? key}) : super(key: key);

  @override
  State<PianoApp> createState() => _PianoAppState();
}

class _PianoAppState extends State<PianoApp> {
  void playSound(int soundNumber){
    final player = AudioPlayer();
    player.play(AssetSource('audio/$soundNumber.mp3'));
  }

  Expanded pianoNote({required String keyName, required Color keyColor, required int noteNumber}){
    Color textColor = Colors.black;
    if(keyColor==Colors.black) textColor = Colors.white;
    return Expanded(
        flex: 2,
        child: TextButton( 
          child: Text(keyName, style: TextStyle(color: textColor, fontWeight: FontWeight.bold),),
          style: TextButton.styleFrom(
            backgroundColor: keyColor,
          ),
          onPressed: (){
            playSound(noteNumber);
          },
        ));
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.white,
        body: SafeArea(
          child: Row(
            children: [
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: <Widget>[
                    pianoNote(keyName: "C", keyColor: Colors.white, noteNumber: 1),
                    pianoNote(keyName: "D", keyColor: Colors.white, noteNumber: 3),
                    pianoNote(keyName: "E", keyColor: Colors.white, noteNumber: 5),
                    pianoNote(keyName: "F", keyColor: Colors.white, noteNumber: 6),
                    pianoNote(keyName: "G", keyColor: Colors.white, noteNumber: 8),
                    pianoNote(keyName: "A", keyColor: Colors.white, noteNumber: 10),
                    pianoNote(keyName: "B", keyColor: Colors.white, noteNumber: 12),
                    pianoNote(keyName: "C", keyColor: Colors.white, noteNumber: 13),
                  ],
                ),
              ),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: <Widget>[
                    Expanded(flex:1, child: Container(color: Colors.transparent,)),
                    pianoNote(keyName: "C#", keyColor: Colors.black, noteNumber: 2),
                    Expanded(flex:1, child: Container(color: Colors.transparent,)),
                    pianoNote(keyName: "Eb", keyColor: Colors.black, noteNumber: 4),
                    Expanded(flex:3, child: Container(color: Colors.transparent,)),
                    pianoNote(keyName: "F#", keyColor: Colors.black, noteNumber: 7),
                    Expanded(flex:1, child: Container(color: Colors.transparent,)),
                    pianoNote(keyName: "G#", keyColor: Colors.black, noteNumber: 9),
                    Expanded(flex:1, child: Container(color: Colors.transparent,)),
                    pianoNote(keyName: "Bb", keyColor: Colors.black, noteNumber: 11),
                    Expanded(flex:4, child: Container(color: Colors.transparent,)),

                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
