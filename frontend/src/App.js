import React, { Component } from 'react';
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import './App.css';
import Head from './components/Head/Head';
import Foot from './components/Foot/Foot';
import Home from './components/Home/Home';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Head />

        <Router>
          <Switch>
            <Route path="/" component={Home} />
          </Switch>
        </Router>

        <Foot />
      </div>
    );
  }
}

export default App;
