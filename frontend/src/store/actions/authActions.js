import { baseUrl } from '../../api/apiInfo';
import {
  SIGNUP_SUCCESS,
  SIGNUP_ERROR,
  LOGIN_ERROR,
  LOGIN_SUCCESS,
  REMOVE_SIGNUP_ERROR
} from '../actionTypes/actionTypes';

export const signup = credentials => {
  return (dispatch, getState) => {
    // call backend service here
    const username = credentials.username;
    const password = credentials.password;
    const url = `${baseUrl}/auth/signup`;
    fetch(url, {
      method: 'POST',
      body: JSON.stringify({ username, password }),
      headers: { 'Content-Type': 'application/json' }
    })
      .then(res => res.json())
      .then(data => {
        console.log(data);
        // const data = res.json();
        // const errors = [];
        // if (res.status === 400) {
        //   if (data.error) errors.push(data.error);
        //   else if (data.errors) errors.push(data.errors);
        //   dispatch({ type: SIGNUP_ERROR, payload: { errors } });
        // }
        // return data;
        let error = {};
        if (data.status !== 200) {
          error = data.error;
          dispatch({ type: SIGNUP_ERROR, payload: { error } });
        } else if (data.status === 200) {
          dispatch({ type: SIGNUP_SUCCESS, payload: { username } });
        }
      });
  };
};

export const removeSignUpError = () => {
  return (dispatch, getState) => {
    dispatch({ type: REMOVE_SIGNUP_ERROR });
  };
};
