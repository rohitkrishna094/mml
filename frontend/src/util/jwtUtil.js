export const saveToken = token => {
  localStorage.removeItem('token');
  localStorage.setItem('token', token);
};

export const removeToken = () => {
  localStorage.removeItem('token');
};

export const isAuthenticated = () => {
  const val = localStorage.getItem('token');
  //   console.log(val + ' : ' + (val !== null));
  return val !== null;
};

export const setHeaders = headers => {
  if (localStorage.getItem('token')) {
    return {
      ...headers,
      Authorization: `Bearer ${localStorage.getItem('token')}`
    };
  } else {
    return headers;
  }
};
