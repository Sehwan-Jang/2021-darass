import { useEffect } from "react";
import { useGetAllComments, useUser } from "../../../hooks";
import { postScrollHeightToParentWindow } from "../../../utils/iframePostMessage";
import CommentArea from "../../templates/CommentArea";

const CommentPage = () => {
  const urlParams = new URLSearchParams(window.location.search);

  const url = urlParams.get("url");
  const projectKey = urlParams.get("projectKey");

  const { user, login, logout } = useUser();
  const { comments } = useGetAllComments({ url, projectKey });

  useEffect(() => {
    postScrollHeightToParentWindow();
  }, [comments]);

  return (
    <CommentArea
      user={user}
      comments={comments}
      onLogin={login}
      onLogout={logout}
      url={url}
      projectSecretKey={projectKey}
    />
  );
};

export default CommentPage;