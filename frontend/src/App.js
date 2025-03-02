import { STATE_LOGIN, STATE_SIGNUP } from 'components/AuthForm';
import GAListener from 'components/GAListener';




import { EmptyLayout, LayoutRoute, MainLayout } from 'components/Layout';
import PageSpinner from 'components/PageSpinner';
import AuthPage from 'pages/AuthPage';
import React from 'react';
import componentQueries from 'react-component-queries';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import './styles/reduction.scss';
import "antd/dist/antd.css";

// const AlertPage = React.lazy(() => import('pages/AlertPage'));
const AuthModalPage = React.lazy(() => import('pages/AuthModalPage'));
// const BadgePage = React.lazy(() => import('pages/BadgePage'));
// const ButtonGroupPage = React.lazy(() => import('pages/ButtonGroupPage'));
// const ButtonPage = React.lazy(() => import('pages/ButtonPage'));
const FolderPage2 = React.lazy(() => import('pages/FolderPage2'));
// const ChartPage = React.lazy(() => import('pages/ChartPage'));
const DashboardPage = React.lazy(() => import('pages/DashboardPage'));
// const DropdownPage = React.lazy(() => import('pages/DropdownPage'));
const FormPage = React.lazy(() => import('pages/FormPage'));
const VideoPage = React.lazy(() => import('pages/VideoPage/VideoPage'));



{/* Added Editor */}
const Editor = React.lazy(()=> import('pages/EditorPage'));
const UploadVideoPage= React.lazy(()=> import('pages/UploadVideoPage'));
// const InputGroupPage = React.lazy(() => import('pages/InputGroupPage'));
// const ModalPage = React.lazy(() => import('pages/ModalPage'));
// const ProgressPage = React.lazy(() => import('pages/ProgressPage'));
// const TablePage = React.lazy(() => import('pages/TablePage'));
// const TypographyPage = React.lazy(() => import('pages/TypographyPage'));
// const WidgetPage = React.lazy(() => import('pages/WidgetPage'));

const getBasename = () => {
  return `/${process.env.PUBLIC_URL.split('/').pop()}`;
};

class App extends React.Component {
  render() {
    return (
      <BrowserRouter basename={getBasename()}>
        <GAListener>
          <Switch>
            <LayoutRoute
              exact
              path="/login"
              layout={EmptyLayout}
              component={props => (
                <AuthPage {...props} authState={STATE_LOGIN} />
              )}
            />
            <LayoutRoute
              exact
              path="/signup"
              //Empty Layout
              layout={EmptyLayout}
              component={props => (
                <AuthPage {...props} authState={STATE_SIGNUP} />
              )}
            />

            <MainLayout breakpoint={this.props.breakpoint}>
              <React.Suspense fallback={<PageSpinner />}>
                <Route exact path="/" component={DashboardPage} />
                <Route exact path='/video/:videoName' component={VideoPage}></Route>
                <Route exact path="/login-modal" component={AuthModalPage} />
                {/* Added New Page for 회의록 폴더 */}
                <Route exact path="/uploadVideo" component={UploadVideoPage} />
                <Route exact path="/folder" component={FolderPage2} />
                {/* Added Editor */}
                <Route exact path="/editor" component={Editor} />
                <Route exact path="/forms" component={FormPage} />
              </React.Suspense>
            </MainLayout>
            <Redirect to="/" />
          </Switch>
        </GAListener>
      </BrowserRouter>
    );
  }
}

const query = ({ width }) => {
  if (width < 575) {
    return { breakpoint: 'xs' };
  }

  if (576 < width && width < 767) {
    return { breakpoint: 'sm' };
  }

  if (768 < width && width < 991) {
    return { breakpoint: 'md' };
  }

  if (992 < width && width < 1199) {
    return { breakpoint: 'lg' };
  }

  if (width > 1200) {
    return { breakpoint: 'xl' };
  }

  return { breakpoint: 'xs' };
};

export default componentQueries(query)(App);
