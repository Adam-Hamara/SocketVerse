import React, { useEffect, useState } from "react";
function Rooms() {
    const [rooms, setRooms] = useState([]);
    const [socket, setSocket] = useState(null);

    useEffect(() => {
        const ws = new WebSocket("ws://localhost:8080/ws");

        ws.onopen = () => {
            console.log("Connected to WebSocket");
            ws.send("/rooms");
        }
        ws.onmessage = (event) => {
            // setMessages(prev => [...prev, event.data])
            const rooms = []
            const roomsArray = JSON.parse(event.data);
            for(var room of roomsArray){
                rooms.push(room)
            }
            setRooms(prev => [...prev, ...rooms]);
        }
        ws.onclose = () => console.log("Disconnected from WebSocket");

        setSocket(ws);
        
        return () => ws.close();
    }, []);

    return (
        <div className="page">
            <h1>Rooms</h1>
            { rooms.length > 0 ? 
                (
                    <ul>
                        { rooms.map((room, index) => (
                            <a href={`/game/${room.gameId}`}> 
                                <p key={index}>{room.gameName}</p> 
                            </a>
                        ))}
                        
                    </ul>
                ) : 
                (
                    <p>Loading rooms...</p>
                )
            }
            
        </div>
    );
}

export default Rooms;
