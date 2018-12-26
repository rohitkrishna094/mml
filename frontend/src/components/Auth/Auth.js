import React, { Component } from 'react';
import { Row, Col, Input, Icon, Button } from 'antd';
import './Auth.css';

export default class Auth extends Component {
  render() {
    return (
      <div style={{ background: '#fff', padding: 24, minHeight: 280 }}>
        <Row type="flex" justify="space-around" align="middle">
          <Col span={4} offset={6}>
            <Input
              placeholder="Enter your username"
              prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
              className="Auth_Input"
            />
            <Input
              placeholder="Enter your password"
              prefix={<Icon type="key" style={{ color: 'rgba(0,0,0,.25)' }} />}
              className="Auth_Input"
            />
            <Input
              placeholder="Confirm your password"
              prefix={<Icon type="key" style={{ color: 'rgba(0,0,0,.25)' }} />}
              className="Auth_Input"
            />
            <Button type="primary" block>
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
