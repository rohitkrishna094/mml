import React, { Component } from 'react';
import './App.css';
import Head from './components/Head/Head';
import Foot from './components/Foot/Foot';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Head />
        <Foot />
      </div>
    );
  }
}

export default App;
