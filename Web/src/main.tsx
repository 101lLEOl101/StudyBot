import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './styles/index.css'
import App from './App.tsx'
import '@mantine/core/styles.css'
import { createTheme, MantineProvider } from "@mantine/core";
import {BrowserRouter} from "react-router-dom";


const theme = createTheme({

});

createRoot(document.getElementById('root')!).render(

    <MantineProvider theme={theme} defaultColorScheme={'dark'}  >
        <StrictMode>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </StrictMode>
    </MantineProvider>
)
