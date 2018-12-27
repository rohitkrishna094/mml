import React, { Component } from 'react';
import { Layout, Menu } from 'antd';
import { NavLink, withRouter } from 'react-router-dom';

const { Header } = Layout;

class Head extends Component {
  state = { selectedKeys: ['1'] };

  componentWillMount() {
    this.makeActiveClass(this.props.location.pathname);
  }

  componentDidUpdate(prevProps, prevState) {
    const prevStateString = JSON.stringify(prevState);
    const stateString = JSON.stringify(this.state);
    if (prevStateString !== stateString) {
      this.makeActiveClass(this.props.location.pathname);
    }
  }

  makeActiveClass(urlName) {
    if (urlName === '/' || urlName === 'home') {
      this.setState({ ...this.state, selectedKeys: ['1'] });
    } else if (urlName === '/movies') {
      this.setState({ ...this.state, selectedKeys: ['2'] });
    } else if (urlName === '/profile') {
      this.setState({ ...this.state, selectedKeys: ['3'] });
    } else if (urlName === '/watchlist') {
      this.setState({ ...this.state, selectedKeys: ['4'] });
    } else if (urlName === '/signup') {
      this.setState({ ...this.state, selectedKeys: ['5'] });
    } else if (urlName === '/login') {
      this.setState({ ...this.state, selectedKeys: ['6'] });
    }
  }

  render() {
    return (
      <Header>
        <div className="logo" />
        <Menu
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={this.state.selectedKeys}
          style={{ lineHeight: '64px' }}
        >
          <Menu.Item key="1">
            <NavLink to="/">Home</NavLink>
          </Menu.Item>
          <Menu.Item key="2">
            <NavLink to="/movies">Movies</NavLink>
          </Menu.Item>
          <Menu.Item key="3">
            <NavLink to="/profile">Profile</NavLink>
          </Menu.Item>
          <Menu.Item key="4">
            <NavLink to="/watchlist">Watchlist</NavLink>
          </Menu.Item>
          <Menu.Item key="5" style={{ float: 'right' }}>
            <NavLink to="/signup">Signup</NavLink>
          </Menu.Item>
          <Menu.Item key="6" style={{ float: 'right' }}>
            <NavLink to="/login">Login</NavLink>
          </Menu.Item>
        </Menu>
      </Header>
    );
  }
}

export default withRouter(Head);
