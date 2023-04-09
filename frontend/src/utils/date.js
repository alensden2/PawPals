// @ts-nocheck

export const getTodayDate = () => {
  const today = new Date();
  const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
  const formattedDate = today.toLocaleDateString('en-GB', options);

  return formattedDate.replace(/\//g, '-');
};
