/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

function bytesToImageUrl(byteArray) {
  const signature = byteArray
    .slice(0, 4)
    .map((b) => b.toString(16))
    .join('');
  let mimeType = 'image/jpeg';
  if (signature === '89504e47') {
    mimeType = 'image/png';
  } else if (signature === '47494638') {
    mimeType = 'image/gif';
  } else if (signature.startsWith('424d')) {
    mimeType = 'image/bmp';
  }
  const blob = new Blob([byteArray], { type: mimeType });
  const urlCreator = window.URL || window.webkitURL;
  return urlCreator.createObjectURL(blob);
}

export { bytesToImageUrl };
