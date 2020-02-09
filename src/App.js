  
import React, { Component } from "react";
import study from "./studier.png";
import mlh from "./mlh.png";
import otta from "./otta.png";
import video from "./output.mp4"
import "./App.css";
import {
  Card, CardImg, CardText, CardBody,
  CardTitle, CardSubtitle, Button
} from 'reactstrap';
import Webcam from 'react-webcam';
import Sound from 'react-sound';

const WebcamComponent = () => <Webcam />;

class App extends Component {
  render() {
    return (
      <div className="App">
        <video className='videoBackground' autoPlay loop muted>
    <source src={video} type='video/mp4' />
</video>
        	<h3><a href="mlh.io"><img border="20" alt="mlhlogo" src={mlh} width="250" height="100"/></a>
	<a href="https://2020.uottahack.ca/"><img border="20" alt="ottalogo" src={otta} width="100" height="100"/></a></h3>
        <header className="App-header">
          <img src={study} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to Ouch!Deslouch</h1>
          <h2 className="App-title">Responsive posture correction</h2>
          <div className="Card">
        <Card>
        <CardBody>
          <br></br>
          <iframe src={Webcam} allow="camera; microphone;" width="500px" height="400px"/>
          <Sound
      url="cool_sound.mp3"
      playStatus={Sound.status.PLAYING}
      playFromPosition={300 /* in milliseconds */}
      onLoading={this.handleSongLoading}
      onPlaying={this.handleSongPlaying}
      onFinishedPlaying={this.handleSongFinishedPlaying}
    />        </CardBody>
      </Card>
      </div>
          <h5>Thousands of office workers suffer from back pain due to chronic bad posture behind the monitor. Look below to check your posture!</h5>
        </header>
      </div>
    );
  }
}

export default App;
