import React, { Component } from 'react';
import { Layout, Menu } from 'antd';
import { Link, withRouter } from 'react-router-dom';

const { Header } = Layout;

class Head extends Component {
  render() {
    return (
      <Header>
        <div className="logo" />
        <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']} style={{ lineHeight: '64px' }}>
          <Menu.Item key="1">
            <Link to="/">Home</Link>
          </Menu.Item>
          <Menu.Item key="2">Movies</Menu.Item>
          <Menu.Item key="3">Profile</Menu.Item>
          <Menu.Item key="4">Watchlist</Menu.Item>
          <Menu.Item key="5" style={{ float: 'right' }}>
            <Link to="/signup">Signup</Link>
          </Menu.Item>
          <Menu.Item key="6" style={{ float: 'right' }}>
            <Link to="/login">Login</Link>
          </Menu.Item>
        </Menu>
      </Header>
    );
  }
}

export default withRouter(Head);
