// @ts-nocheck

const getMimeTypeFromBytes = (bytes) => {
  const header = bytes
    .slice(0, 4)
    .reduce((acc, byte) => acc + byte.toString(16), '');
  switch (header) {
    case '89504e47':
      return 'image/png';
    case '47494638':
      return 'image/gif';
    case 'ffd8ffe0':
    case 'ffd8ffe1':
    case 'ffd8ffe2':
      return 'image/jpeg';
    default:
      return '';
  }
};

const getImageUrlFromBytes = ({ bytes }) => {
  const mimeType = getMimeTypeFromBytes(bytes);
  const base64String = btoa(
    new Uint8Array(bytes).reduce(
      (data, byte) => data + String.fromCharCode(byte),
      ''
    )
  );

  return `data:${mimeType};base64,${base64String}`;
};

export { getImageUrlFromBytes };
