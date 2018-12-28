import React, { Component } from 'react';
import { Layout, Menu, Icon } from 'antd';
import { NavLink, withRouter } from 'react-router-dom';
import { isAuthenticated, logout, getCurrentUserName } from '../../util/jwtUtil';

const { Header } = Layout;
const SubMenu = Menu.SubMenu;

class Head extends Component {
  state = { selectedKeys: '1' };

  shouldComponentUpdate(nextProps) {
    return nextProps.location !== this.props.location;
  }
  componentWillMount() {
    this.makeActiveClass(this.props.location.pathname);
  }

  componentWillReceiveProps(nextProps) {
    this.makeActiveClass(nextProps.location.pathname);
  }

  makeActiveClass = urlName => {
    this.setState({ ...this.state, selectedKeys: this.mapUrlToKey(urlName) });
  };

  onLogoutClick = e => {
    logout();
    this.props.history.push('/login');
  };

  mapUrlToKey = urlName => {
    if (urlName === '/' || urlName === 'home') {
      return '1';
    } else if (urlName === '/movies') {
      return '2';
    } else if (urlName === '/profile') {
      return '3';
    } else if (urlName === '/watchlist') {
      return '4';
    } else if (urlName === '/signup') {
      return '5';
    } else if (urlName === '/login') {
      return '6';
    }
    return '7';
  };

  render() {
    let loginButton, signupButton, userNode;
    if (!isAuthenticated()) {
      signupButton = (
        <Menu.Item key="5" style={{ float: 'right' }}>
          <NavLink to="/signup">Signup</NavLink>
        </Menu.Item>
      );
      loginButton = (
        <Menu.Item key="6" style={{ float: 'right' }}>
          <NavLink to="/login">Login</NavLink>
        </Menu.Item>
      );
      userNode = null;
    } else {
      userNode = (
        <SubMenu
          title={
            <span className="submenu-title-wrapper">
              {getCurrentUserName()}&nbsp;
              <Icon type="caret-down" />
            </span>
          }
          style={{ float: 'right' }}
        >
          <Menu.Item key="setting:1" onClick={e => this.onLogoutClick(e)}>
            Logout
          </Menu.Item>
        </SubMenu>
      );
      loginButton = null;
      signupButton = null;
    }
    return (
      <Header>
        <div className="logo" />
        <Menu theme="dark" mode="horizontal" selectedKeys={[this.state.selectedKeys]} style={{ lineHeight: '64px' }}>
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
          {signupButton}
          {loginButton}
          {userNode}
        </Menu>
      </Header>
    );
  }
}

export default withRouter(Head);
