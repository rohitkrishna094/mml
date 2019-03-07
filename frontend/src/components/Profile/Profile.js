import React, { Component } from 'react';
import 'antd/dist/antd.css';
import { Upload, message, Button, Icon } from 'antd';

class Profile extends Component {
  render() {
    const props = {
      name: 'file',
      action:
        'http://ec2-52-24-8-126.us-west-2.compute.amazonaws.com:8080/candidate/5c1aae39f37a6f35c4d84586/documents/save',
      headers: {
        // 'content-type': 'multipart/form-data'
      },
      onChange(info) {
        if (info.file.status !== 'uploading') {
          console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
          message.success(`${info.file.name} file uploaded successfully`);
        } else if (info.file.status === 'error') {
          message.error(`${info.file.name} file upload failed.`);
        }
      }
    };
    return (
      <Upload {...props}>
        <Button>
          <Icon type="upload" /> Click to Upload
        </Button>
      </Upload>
    );
  }
}

export default Profile;
