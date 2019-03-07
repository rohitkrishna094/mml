import { baseUrl } from '../../api/apiInfo';
import { LOAD_MOVIES } from '../actionTypes/actionTypes';

export const getMovies = () => {
  return (dispatch, getState) => {
    const url = `${baseUrl}/movies/search`;
    fetch(url, { headers: { 'Content-Type': 'application/json' } })
      .then(res => res.json())
      .then(data => {
        dispatch({ type: LOAD_MOVIES, payload: { data } });
      });
  };
};
