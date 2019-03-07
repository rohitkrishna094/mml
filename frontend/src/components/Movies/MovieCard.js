import React from 'react';
import { Row, Col, Card, Icon, Avatar } from 'antd';

const { Meta } = Card;
const MovieCard = props => {
  const { title, coverImage } = props;
  return (
    <Col span={4} style={{ margin: '10px -30px 0px 0px' }}>
      <Card
        style={{ width: 200 }}
        cover={<img alt="example" src={coverImage} />}
        // actions={[<Icon type="setting" />, <Icon type="edit" />, <Icon type="ellipsis" />]}
      >
        <Meta title={title} />
      </Card>
    </Col>
  );
};

export default MovieCard;
