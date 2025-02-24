import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Magic8Ball from './components/Magic8ball.js';
import WebSocketComponent from './components/WebSocketComponent.js'
import Home from './components/Home.js';
import './App.css'; // Import the CSS file
import NotFound from './components/NotFound.js';
import Rooms from './components/Rooms.js';
import { useEffect, useState } from 'react';
import webSocketService from './service/WebSocketService.js';
import Games from './components/Games.js';
import Pioupiou from './components/Pioupiou.js';

function App() {
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    
    webSocketService.connect("ws://localhost:8080/ws");
    return () => {
      webSocketService.disconnect();
  };
  }, []);

  return (
    <Router>
     <nav style={{ display: "flex", justifyContent: "space-between", alignItems: "center", padding: "10px" }}>
        <ul style={{ display: "flex", listStyle: "none", gap: "20px", margin: 0, padding: 0 }}>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/web-socket">WebSocket</Link>
          </li>
          <li>
            <Link to="/rooms">Rooms</Link>
          </li>
          <li>
            <Link to="/games">Games</Link>
          </li>
        </ul>
        {
          localStorage.getItem("Nickname") !== null ? (
            <div>
              <button
                onClick={() => setIsOpen(!isOpen)}
                className="bg-gray-700 px-4 py-2 rounded-md"
              >
              {localStorage.getItem("Nickname")} ▼
            </button>

          {isOpen && (
            <div className="absolute right-0 mt-2 w-40 bg-white text-black rounded-md shadow-lg">
              <ul className="p-2">
                <li>
                  <button className="block px-4 py-2 hover:bg-gray-200" onClick={() => { localStorage.removeItem("Nickname"); window.location.href = "/"; }}>
                    Delete
                  </button>
                </li>
              </ul>
            </div>
          )}
        </div>
          ) : (<div></div>)
        }

        
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/game/magic8ball/*" element={<Magic8Ball />} />
        <Route path="/web-socket" element={<WebSocketComponent />} />
        <Route path="/rooms" element={<Rooms />} />
        <Route path="/games" element={<Games />} />
        <Route path="/game/pioupiou/*" element={<Pioupiou />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
