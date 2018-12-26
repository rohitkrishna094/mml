import React, { Component } from 'react';
import './App.css';
import Head from './components/Head/Head';
import Foot from './components/Foot/Foot';
import Home from './components/Home/Home';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Head />
        <Home />
        <Foot />
      </div>
    );
  }
}

export default App;
