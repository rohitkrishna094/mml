import { LOAD_MOVIES } from '../actionTypes/actionTypes';

const intialState = { movies: [] };
const moviesReducer = (state = intialState, action) => {
  switch (action.type) {
    case LOAD_MOVIES:
      return { ...state, movies: action.payload.data };
    default:
      return state;
  }
};

export default moviesReducer;
