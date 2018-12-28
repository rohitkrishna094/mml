import React, { Component } from 'react';
import { Row, Col, Card, Icon, Avatar } from 'antd';
import MovieCard from './MovieCard';

const { Meta } = Card;
export default class Movies extends Component {
  state = { n: 8 };

  render() {
    return (
      <Row type="flex" justify="center" align="middle" style={{ textAlign: 'center' }}>
        <Col span={4}>search</Col>
        <Col span={20}>
          {/* {[...Array(this.state.n)].map((e, i) => (
            <MovieCard />
          ))} */}

          <Row gutter={24}>
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
          </Row>
        </Col>
      </Row>
    );
  }
}
