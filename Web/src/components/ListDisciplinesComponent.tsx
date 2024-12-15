import {ActionIcon, Badge, Box, Group, Loader, Notification, Table} from '@mantine/core';
import {Link} from "react-router-dom";
import {IconPlus} from "@tabler/icons-react";
import {useQuery} from "@tanstack/react-query";
import {fetchDisciplines} from "../api/service.ts";
import {stringToColour} from "../stringToColour.ts";

export default function ListDisciplinesComponent() {
    const {status, data, error } = useQuery(["disciplines"], fetchDisciplines);
    if(status === "loading") {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Loader size="lg"/>
            </Box>
        )
    } else if (status === "error") {
        return (
            <Notification color="red" title="Error loading">
                {error.message || 'An unknown error occurred.'}
            </Notification>
        )
    }
    const disciplines = data.data
    const rows = disciplines.map((item) => (
        <Table.Tr key={item.disciplineName}>
            <Table.Td ta={"left"}>
                <Group gap="sm">
                    <Badge color={stringToColour(item.disciplineName)} fz="sm" fw={500}>
                        {item.disciplineName}
                    </Badge>
                </Group>
            </Table.Td>
            <Table.Td ta={"right"}>
                <Link to={"/create-test"}>
                    <ActionIcon variant="subtle" color="gray">
                        <IconPlus>
                        </IconPlus>
                    </ActionIcon>
                </Link>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Thead>
                    <Table.Tr>
                        <Table.Th ta={"left"}>Название Дисциплины</Table.Th>
                    </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}