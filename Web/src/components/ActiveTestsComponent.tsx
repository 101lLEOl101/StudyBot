import {Badge, Box, Group, Loader, Notification, Table, Text} from '@mantine/core';
import {stringToColour} from "../stringToColour.ts";
import {useQuery} from "@tanstack/react-query";
import {fetchActiveTests} from "../api/service.ts";

export default function ActiveTestsComponent() {
    const {status, data, error } = useQuery(["active-tests"], fetchActiveTests);
    if (status === "loading") {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Loader size="lg"/>
            </Box>
        );
    }

    if (status === "error") {
        return (
            <Notification color="red" title="Error loading">
                {error.message || 'An unknown error occurred.'}
            </Notification>
        );
    }

    // Check if there are no tests
    if (data?.data?.length === 0) {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Text color="dimmed" size="lg">
                    Нет активных тестов
                </Text>
            </Box>
        );
    }

    const rows = data.data.map((item) => (
        <Table.Tr key={item.testName}>
            <Table.Td ta={"left"}>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.testName}
                    </Text>
                </Group>
            </Table.Td>

            <Table.Td ta={"center"}>
                <Badge color={stringToColour(item.disciplineName)} >
                    {item.disciplineName}
                </Badge>
            </Table.Td>
            <Table.Td ta={"right"}>
                <Text size="sm">
                    {item.expiresTime}
                </Text>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Thead>
                    <Table.Tr>
                        <Table.Th ta={"left"}>Название Теста</Table.Th>
                        <Table.Th ta={"center"}>Дисциплина</Table.Th>
                        <Table.Th ta={"right"}>Окончание</Table.Th>
                    </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}