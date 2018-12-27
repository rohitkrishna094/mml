import React, { Component } from 'react';
import { Row, Col, Input, Icon, Button, notification } from 'antd';
import { connect } from 'react-redux';
import './Auth.css';
import { signup, removeSignUpError } from '../../store/actions/authActions';

class Auth extends Component {
  state = { signUpCredentials: { username: '', password: '' }, loginCredentials: { username: '', password: '' } };

  onSignUpInputChange = e => {
    this.setState({
      ...this.state,
      signUpCredentials: {
        ...this.state.signUpCredentials,
        [e.target.name]: e.target.value
      }
    });
  };

  componentWillReceiveProps(nextProps) {
    if (nextProps.signedUp) {
      this.setState({
        ...this.state,
        notificationNode: this.openNotification(
          'Signup Success',
          'You have been successfully signed up. Please login with same credentials to continue.'
        )
      });
    } else if (nextProps.signedUpError && Object.keys(nextProps.signedUpError).length !== 0) {
      this.setState({
        ...this.state,
        notificationNode: this.openNotification('Signup Error', 'Error while sign up: ' + nextProps.signedUpError)
      });
      this.props.removeSignUpError();
    } else {
      this.setState({ ...this.state, notificationNode: null });
    }
  }

  openNotification = (message, description) => {
    notification.open({
      message: message,
      description: description,
      onClick: () => {
        console.log('Notification Clicked!');
      }
    });
  };

  render() {
    return (
      <div style={{ background: '#fff', padding: 24, minHeight: 280 }}>
        {this.state.notificationNode ? this.state.notificationNode : null};
        <Row type="flex" justify="space-around" align="middle">
          <Col span={4} offset={6}>
            <Input
              placeholder="Enter your username"
              prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
              className="Auth_Input"
              onChange={e => this.onSignUpInputChange(e)}
              name="username"
            />
            <Input
              placeholder="Enter your password"
              prefix={<Icon type="key" style={{ color: 'rgba(0,0,0,.25)' }} />}
              className="Auth_Input"
              onChange={e => this.onSignUpInputChange(e)}
              name="password"
              type="password"
            />
            <Button type="primary" block onClick={e => this.props.signup(this.state.signUpCredentials)}>
              Sign Up
            </Button>
          </Col>
          <Col span={2} pull={2}>
            <div style={{ borderLeft: '1px solid rgba(0,0,0,.25)', height: '150px' }} />
          </Col>
          <Col span={4} pull={6}>
            <Input
              placeholder="Enter your username"
              prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
              className="Auth_Input"
            />
            <Input
              placeholder="Enter your password"
              prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
              className="Auth_Input"
              type="password"
            />
            <Button type="danger" block>
              Login
            </Button>
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    signedUp: state.auth.signedUp,
    signedUpError: state.auth.error
  };
};
const mapDispatchToProps = dispatch => {
  return {
    signup: signUpCredentials => dispatch(signup(signUpCredentials)),
    removeSignUpError: () => dispatch(removeSignUpError())
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Auth);
