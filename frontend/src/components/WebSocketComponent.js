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

    return (
        <div>
            <button onClick={sendMessage}>Send Message</button>
            <ul>
                {messages.map((msg, index) => <li key={index}>{msg}</li>)}
            </ul>
        </div>
    );
}

export default WebSocketComponent;
