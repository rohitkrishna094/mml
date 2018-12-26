import React, { Component } from 'react';
import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';
import './App.css';
import Head from './components/Head/Head';
import Foot from './components/Foot/Foot';
import Home from './components/Home/Home';
import Auth from './components/Auth/Auth';
import Watchlist from './components/Watchlist/Watchlist';
import Profile from './components/Profile/Profile';
import Movies from './components/Movies/Movies';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Router>
          <div>
            <Head />
            <Switch>
              <Route path="/login" exact component={Auth} />
              <Route path="/signup" exact component={Auth} />
              <Route path="/watchlist" exact component={Watchlist} />
              <Route path="/profile" exact component={Profile} />
              <Route path="/movies" exact component={Movies} />
              <Route path="/" component={Home} />
            </Switch>
            <Foot />
          </div>
        </Router>
      </div>
    );
  }
}

export default App;
