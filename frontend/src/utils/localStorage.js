/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

export const localStorageUtil = {
  setItem: function (key, value) {
    localStorage.setItem(key, JSON.stringify(value));
  },

  getItem: function (key) {
    const value = localStorage.getItem(key);
    return value ? JSON.parse(value) : null;
  },

  removeItem: function (key) {
    localStorage.removeItem(key);
  },

  clear: function () {
    localStorage.clear();
  }
};
