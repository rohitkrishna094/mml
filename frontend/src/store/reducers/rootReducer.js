import { combineReducers } from 'redux';
import authReducer from './authReducer';
import moviesReducer from './moviesReducer';

const rootReducer = combineReducers({
  auth: authReducer,
  movies: moviesReducer
});

export default rootReducer;
