import { useNavigate, NavigateOptions } from 'react-router-dom';

type NavigateFunction = (route: string, options?: NavigateOptions) => void;

const useCustomNavigate = (): NavigateFunction => {
  const navigate = useNavigate();

  const customNavigate: NavigateFunction = (route, options) => {
    navigate(route, options);
  };

  return customNavigate;
};

export default useCustomNavigate;
