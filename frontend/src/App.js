// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Magic8Ball from './components/Magic8ball.js';
import WebSocketComponent from './components/WebSocketComponent.js'
import Home from './components/Home.js';
import './App.css'; // Import the CSS file
import NotFound from './components/NotFound.js';
import Rooms from './components/Rooms.js';

function App() {
  return (
    <Router>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/web-socket">WebSocket</Link>
          </li>
          <li>
            <Link to="/rooms">Rooms</Link>
          </li>
        </ul>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/game/magic8ball" element={<Magic8Ball />} />
        <Route path="/web-socket" element={<WebSocketComponent />} />
        <Route path="/rooms" element={<Rooms />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
