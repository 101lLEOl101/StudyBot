import {AuthenticationComponent} from "./AuthenticationComponent.tsx";
import {Box} from "@mantine/core";
import {QueryClientProvider} from "@tanstack/react-query";
import {queryClient} from "../api/query-client.ts";


export default function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <Box maw={420} ml={"auto"} mr={"auto"} pt={"calc(60vh - 359px)"}>
                <AuthenticationComponent>

                </AuthenticationComponent>
            </Box>
        </QueryClientProvider>
    )
}
