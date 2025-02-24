import React, { useEffect, useState } from "react";
import webSocketService from "../service/WebSocketService";
function Rooms() {
    const [rooms, setRooms] = useState([]);

    useEffect(() => {

        const messageHandler = (message) => {
            const rooms = []
            const roomsArray = JSON.parse(message);
            for(var room of roomsArray){
                rooms.push(room)
            }
            setRooms(rooms);
        };

        webSocketService.addListener("rooms", messageHandler);

        webSocketService.send('/list_rooms')

        return () => {
            webSocketService.removeListener("rooms");
        };
        
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
