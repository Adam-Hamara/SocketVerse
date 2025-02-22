import React, { useEffect, useState } from "react";
function WebSocketComponent() {
    const [messages, setMessages] = useState([]);
    const [socket, setSocket] = useState(null);

    useEffect(() => {
        const ws = new WebSocket("ws://localhost:8080/ws");

        ws.onopen = () => console.log("Connected to WebSocket");
        ws.onmessage = (event) => setMessages(prev => [...prev, event.data]);
        ws.onclose = () => console.log("Disconnected from WebSocket");

        setSocket(ws);

        return () => ws.close();
    }, []);

    const sendMessage = () => {
        if (socket) socket.send("Hello from React!");
    };

    const join = () => {
        if (socket) socket.send("/join magic8ball");
    }

    return (
        <div className="page">
            <h1>🚀 Websocket test</h1>
            <button onClick={sendMessage}>Test connection</button>
            <button onClick={join}>Join</button>
            <div>
                <ul>
                    {messages.map((msg, index) => <li key={index}>{msg}</li>)}
                </ul>
            </div>
        </div>
    );
}

export default WebSocketComponent;
