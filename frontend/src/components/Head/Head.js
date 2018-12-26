import React, { Component } from 'react';
import { Layout, Menu } from 'antd';

const { Header } = Layout;

export default class Head extends Component {
  render() {
    return (
      <Header>
        <div className="logo" />
        <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']} style={{ lineHeight: '64px' }}>
          <Menu.Item key="1">Home</Menu.Item>
          <Menu.Item key="2">Movies</Menu.Item>
          <Menu.Item key="3">Profile</Menu.Item>
          <Menu.Item key="4">Watchlist</Menu.Item>
        </Menu>
      </Header>
    );
  }
}
