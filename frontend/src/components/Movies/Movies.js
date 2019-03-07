import React, { Component } from 'react';
import { Row, Col, Card, Icon, Avatar } from 'antd';
import MovieCard from './MovieCard';
import { connect } from 'react-redux';
import { getMovies } from '../../store/actions/movieActions';

const { Meta } = Card;
class Movies extends Component {
  state = { n: 8 };

  componentDidMount() {
    if (this.props.getMovies) {
      this.props.getMovies();
    }
  }

  render() {
    return (
      <Row type="flex" justify="center" align="middle" style={{ textAlign: 'center' }}>
        <Col span={4}>search</Col>
        <Col span={20}>
          <Row gutter={24}>
            {/* {[...Array(this.state.n)].map((e, i) => (
              <MovieCard key={Math.random()} />
            ))} */}
            {this.props.movies.map(e => (
              <MovieCard key={e.id} title={e.title} coverImage={e.poster} />
              // <p>
              //   {e.id} {e.title} {e.poster}
              // </p>
            ))}
          </Row>

          {/* <Row gutter={24}>
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
            <MovieCard />
          </Row> */}
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = state => {
  return {
    movies: state.movies.movies
  };
};

const mapDistachToProps = dispatch => {
  return {
    getMovies: () => dispatch(getMovies())
  };
};

export default connect(
  mapStateToProps,
  mapDistachToProps
)(Movies);
