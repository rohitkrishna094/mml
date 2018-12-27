import { SIGNUP_SUCCESS, SIGNUP_ERROR } from '../actionTypes/actionTypes';

const initialState = { signedUp: false, error: {} };
const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case SIGNUP_SUCCESS:
      return { ...state, signedUp: true, error: {} };
    case SIGNUP_ERROR:
      return { ...state, signedUp: false, error: action.payload.error };
    default:
      return state;
  }
};

export default authReducer;
