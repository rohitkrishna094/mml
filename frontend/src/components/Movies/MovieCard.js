import React from 'react';
import { Row, Col, Card, Icon, Avatar } from 'antd';

const { Meta } = Card;
const MovieCard = () => {
  return (
    <Col span={4} style={{ margin: '10px -30px 0px 0px' }}>
      <Card
        style={{ width: 200 }}
        cover={<img alt="example" src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png" />}
        // actions={[<Icon type="setting" />, <Icon type="edit" />, <Icon type="ellipsis" />]}
      >
        <Meta title="Card title" />
      </Card>
    </Col>
  );
};

export default MovieCard;
